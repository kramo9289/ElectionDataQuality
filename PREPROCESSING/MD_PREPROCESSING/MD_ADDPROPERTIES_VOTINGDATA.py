import json
import csv

MD_PRECINCT_FILEPATH = 'md_precincts.json'
MD_2016_FILEPATH = 'All_By_Precinct_2016_General.csv'
MD_2018_FILEPATH = 'All_By_Precinct_2018_General.csv'
MD_OUTPUT_FILEPATH = 'complete_md_precinct.geojson'
MD_REMOVE_PROPERTIES = ['NAME','CNG02', 'COUNTY', 'LEG02', 'VTD', 'MCD', 'STATE', 'VTD_1',
                        'PLACE', 'UNADJPOP', 'ADJ_POPULA', 'ADJ_WHITE', 'ADJ_BLACK',
                        'ADJ_AMINDI', 'ADJ_ASIAN', 'ADJ_HAWAII', 'ADJ_OTHER', 'ADJ_18__PO',
                        'ADJ2_RACES', 'UNADJHISP', 'ADJ_18__WH', 'ADJ_18__BL', 'ADJ_18__IN',
                        'ADJ_18__AS', 'ADJ_18__HW', 'ADJ_18__OT' , 'HU_OCCUPIE', 'HOUSING_UN']

MD_COUNTY_DICTIONARY = {'1':'Allegany', '2':'Anne Arundel', '3':'Baltimore City', '4':'Baltimore County',
                        '5':'Calvert', '6':'Caroline', '7':'Carroll', '8':'Cecil', '9':'Charles',
                        '10':'Dorchester', '11':'Frederick', '12':'Garrett', '13':'Harford',
                        '14':'Howard', '15':'Kent', '16':'Montgomery', '17':"Prince George's",
                        '18':"Queen Anne's", '19':"St. Mary's", '20':'Somerset', '21':'Talbot',
                        '22':'Washington', '23':'Wicomico', '24':'Worcester'}

PRECINCT_UPDATED_DATA = None
PRECINCT_DICTIONARY = {}

def main():
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY
    
    with open(MD_PRECINCT_FILEPATH, 'r') as PRECINCT_JSON:
        PRECINCT_UPDATED_DATA = json.load(PRECINCT_JSON)
        
        for feature in PRECINCT_UPDATED_DATA['features']:
            properties = feature['properties']
            properties_dict = {'totvot16':0, 'totvot18':0, 'totvotpres':0,
                        'repvot16':0, 'repvot18':0, 'repvotpres':0,
                        'demvot16':0, 'demvot18':0, 'demvotpres':0,
                        'othvot16':0, 'othvot18':0, 'othvotpres':0,
                        'pop100' : int(properties['ADJ_POPULA']),
                        'nhisov18' : int(properties['ADJ_18__WH'])+int(properties['ADJ_18__BL'])
                        +int(properties['ADJ_18__BL'])+int(properties['ADJ_18__IN'])+int(properties['ADJ_18__AS'])
                        +int(properties['ADJ_18__HW'])+int(properties['ADJ_18__OT']),
                        'hisov18' : int(properties['UNADJHISP']),
                        'whiteov18' : int(properties['ADJ_18__WH']),
                        'blackov18' : int(properties['ADJ_18__BL']),
                        'aminov18' : int(properties['ADJ_18__IN']),
                        'asianov18' : int(properties['ADJ_18__AS']),
                        'hawov18' : int(properties['ADJ_18__HW']),
                        'otherov18' : int(properties['ADJ_18__OT']),
                        'statefp' : properties['STATE'],
                        'name' : properties['NAME'],
                        'origname' : properties['NAME'],
                        'countyname' : properties['NAME'][:-16].lower(),
                        'districtid' : properties['CNG02'][2:].lstrip('0')
            }
            feature['properties'].update(properties_dict)
            #Use the voting disctrict(precinct) unique identifier as a key to save the location of the geospatial data for that specific precinct
            PRECINCT_DICTIONARY[feature['properties']['NAME']] = feature['properties']
            
    electionDataScript(MD_2016_FILEPATH, False)
    electionDataScript(MD_2018_FILEPATH, True)
    removePropertiesFromJson(MD_REMOVE_PROPERTIES)
    
    #Create a new file and add the new updated json data to the file
    with open(MD_OUTPUT_FILEPATH, 'w') as OUTPUT_GEOJSON:
        json.dump(PRECINCT_UPDATED_DATA, OUTPUT_GEOJSON)
        print("Added properties to output file: " +  MD_OUTPUT_FILEPATH)
    
def removePropertiesFromJson(properties):
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY
    for feature in PRECINCT_UPDATED_DATA['features']:
        for prop in properties:
            del feature['properties'][prop]
    
def electionDataScript(ELECTION_FILEPATH, IS_2018):
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY

    with open(ELECTION_FILEPATH, 'r') as ELECTION_CSV:
        ELECTION_READER = csv.DictReader(ELECTION_CSV)
        for rows in ELECTION_READER:
            properties_dict = {}
            csv_county = rows['County'].lstrip('0')
            csv_county = MD_COUNTY_DICTIONARY[csv_county]
            csv_district = rows['Election District'][1:]
            csv_precinct = rows['Election Precinct']
            csv_party = rows['Party'].lower()
            csv_election_type = rows['Office Name']
            csv_votes = rows['Election Night Votes']
            csv_cong = rows['Cong']

            if((csv_county == 'Garrett' or csv_county == 'Washington') and csv_precinct == '000'):
                csv_precinct = '001'

            if(csv_county == 'Allegany' and csv_district == '22' and csv_precinct == '001'):
                csv_precinct = '000'
            
            if(csv_party != 'rep' and csv_party != 'dem'):
                csv_party = 'oth'
            
            csv_key = csv_county + ' Precinct ' + csv_district + '-' + csv_precinct
            if(csv_key in PRECINCT_DICTIONARY.keys()):
                json_feature = PRECINCT_DICTIONARY[csv_key]
                properties_dict['origname'] = json_feature['NAME']
                properties_dict['countyname'] = csv_county.lower()
                
                if('Rep in Congress' in csv_election_type and not IS_2018):
                    json_feature[csv_party + 'vot16'] += int(csv_votes)
                    json_feature['totvot16'] += int(csv_votes)
                if('President - Vice Pres' in csv_election_type):
                    json_feature[csv_party + 'votpres'] += int(csv_votes)
                    json_feature['totvotpres'] += int(csv_votes)
                if('Representative in Congress' in csv_election_type and IS_2018):
                    json_feature[csv_party + 'vot18'] += int(csv_votes)
                    json_feature['totvot18']  += int(csv_votes)

                json_feature.update(properties_dict)
                
if __name__ == "__main__":
    main()
                            
