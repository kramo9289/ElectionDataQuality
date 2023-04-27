import json
import csv

RI_PRECINCT_FILEPATH = 'ri_precinct_demographic_result.json'
RI_2016_FILEPATH = 'rigen2016l_congressional.txt'
RI_PRES_FILEPATH = 'rigen2016l_pres.txt'
RI_2018_FILEPATH = 'rigen2018l_congressional.txt'
RI_OUTPUT_FILEPATH = 'complete_ri_precinct.geojson'
RI_REMOVE_PROPERTIES = ['NAME','OBJECTID', 'Shape__Are', 'Shape__Len']

PRECINCT_UPDATED_DATA = None
PRECINCT_DICTIONARY = {}

def main():
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY

    properties_dict = {'totvot16':0, 'totvot18':0, 'totvotpres':0,
                       'repvot16':0, 'repvot18':0, 'repvotpres':0,
                       'demvot16':0, 'demvot18':0, 'demvotpres':0,
                       'othvot16':0, 'othvot18':0, 'othvotpres':0,}
    
    with open(RI_PRECINCT_FILEPATH, 'r') as PRECINCT_JSON:
        PRECINCT_UPDATED_DATA = json.load(PRECINCT_JSON)
        
        for feature in PRECINCT_UPDATED_DATA['features']:
            feature['properties'].update(properties_dict)
            #Use the voting disctrict(precinct) unique identifier as a key to save the location of the geospatial data for that specific precinct
            PRECINCT_DICTIONARY[feature['properties']['NAME']] = feature['properties']

    electionDataScript(RI_2016_FILEPATH, '16')
    electionDataScript(RI_PRES_FILEPATH, 'pres')
    electionDataScript(RI_2018_FILEPATH, '18')
    removePropertiesFromJson(RI_REMOVE_PROPERTIES)
    
    #Create a new file and add the new updated json data to the file
    with open(RI_OUTPUT_FILEPATH, 'w') as OUTPUT_GEOJSON:
        json.dump(PRECINCT_UPDATED_DATA, OUTPUT_GEOJSON)
        print("Added properties to output file: " +  RI_OUTPUT_FILEPATH)
    
def removePropertiesFromJson(properties):
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY
    for feature in PRECINCT_UPDATED_DATA['features']:
        for prop in properties:
            del feature['properties'][prop]
    
def electionDataScript(ELECTION_FILEPATH, ELECTION):
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY
    district_id = '1'
    electionfile = open(ELECTION_FILEPATH, "r")
    for line in electionfile:
        properties_dict = {}
        election_information = line.split("-")[0]
        precinct_name = line.split("-")[1]
        
        #Break up the fields using the data_description file and splice
        precinct_code = election_information[7:11]
        total_votes = election_information[11:17]
        party_code = election_information[101:104].lower()
        if(party_code != 'rep' and party_code != 'dem'):
            party_code = 'oth'
        properties_dict['origname'] = precinct_code
        properties_dict['name'] = precinct_name.replace('\n', '')
        properties_dict['countyname'] = properties_dict['name'][:-5].lower()
        properties_dict['statefp'] = '44'

        if(ELECTION == '16'):
            if(properties_dict['name'] == 'Burrillville 0301'):
                district_id = '2'
            properties_dict['districtid'] = district_id
        if(precinct_code in PRECINCT_DICTIONARY.keys()):
            json_feature = PRECINCT_DICTIONARY[precinct_code]
            json_feature[party_code + 'vot' + ELECTION] = json_feature[party_code + 'vot' + ELECTION] + int(total_votes)
            json_feature['totvot' + ELECTION] = json_feature['totvot' + ELECTION] + int(total_votes)
                         
            json_feature.update(properties_dict)
    
    electionfile.close()

if __name__ == "__main__":
    main()
                            
