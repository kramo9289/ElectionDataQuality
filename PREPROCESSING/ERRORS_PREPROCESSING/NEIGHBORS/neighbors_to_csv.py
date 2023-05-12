import json
import csv

csv_dict = []
csv_columns = ['id', 'neighbor']
check_previous_neighbors  = {}

with open('neighbors.json', 'r') as json_file:
    data = json.load(json_file)
    for prop in data['features']:
        neighbors = prop['properties']['neighbors']
        neighbors_arr = neighbors.split(', ')
        ogr_fid = prop['properties']['id']
        for n in neighbors_arr:
            if(n == ''):
                continue
            #if(int(n) in check_previous_neighbors.keys()):
                #if(str(ogr_fid) in check_previous_neighbors[int(n)]):                    continue
            neighbor_dict = {}
            neighbor_dict['id'] = int(ogr_fid)
            neighbor_dict['neighbor'] = int(n)
            csv_dict.append(neighbor_dict)

        #check_previous_neighbors[int(ogr_fid)] = neighbors_arr
        
with open('all_precinct_neighbors.csv', 'w', newline='') as csvfile:
    write = csv.DictWriter(csvfile, fieldnames = csv_columns)
    write.writeheader()
    for row in csv_dict:
        write.writerow(row)
    print('exported neighbors csv')
