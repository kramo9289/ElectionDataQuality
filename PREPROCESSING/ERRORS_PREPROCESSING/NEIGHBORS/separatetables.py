import json
import geopandas
import pandas
import maup
import warnings
from shapely.geometry import shape

#Debugging purposes, ignore the warning line
warnings.filterwarnings('ignore','GeoSeries.isna',UserWarning)

with open('test.json', 'r') as json_file:
    data = json.load(json_file)
    for d in data:
        d['geometry'] = shape(d['geometry'])

    precincts = geopandas.GeoDataFrame(data).set_geometry('geometry')
    precincts["neighbors"] = None

    for index, row in precincts.iterrows():
        neighbors = precincts[precincts.geometry.touches(row.geometry)].id.tolist()
        if(row.id in neighbors):
            neighbors.remove(row.id)

        for i in range(len(neighbors)):
            neighbors[i] = str(neighbors[i])
        precincts.at[index, "neighbors"] = ", ".join(neighbors)
        
    with open('neighbors.json', 'w') as f:
        f.write(precincts.to_json())
