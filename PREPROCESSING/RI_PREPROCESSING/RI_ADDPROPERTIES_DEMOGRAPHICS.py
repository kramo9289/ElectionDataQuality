import json
import csv

#File paths for all necessary files
RI_CENSUSBLOCK_FILEPATH = 'ri_censusblock.json'
RI_BLOCKDEMOGRAPHICS_FILEPATH = 'ri_blockdemographics.csv'
RI_OUTPUT_FILEPATH = 'complete_ri_censusblock.geojson'

#Open the json containing the censusblocks for RI
with open(RI_CENSUSBLOCK_FILEPATH, 'r') as RI_CENSUSBLOCK_JSON:
    CENSUSBLOCK_DATA = json.load(RI_CENSUSBLOCK_JSON)
    #Create a dictionary to map a tract+block key to the appriopriate location in CENSUSBLOCK_DATA
    CENSUSBLOCK_DICTIONARY = {}
    
    for feature in CENSUSBLOCK_DATA['features']:
        json_tract = feature['properties']['TRACTCE10']
        json_block = feature['properties']['BLOCKCE']
        json_key = json_tract+json_block
        CENSUSBLOCK_DICTIONARY[json_key] = feature['properties']

#Retrieve the infromation from the csv file that will be injected into the properties of the json file
with open(RI_BLOCKDEMOGRAPHICS_FILEPATH, 'r') as RI_BLOCKDEMOGRAPHICS_CSV:
    RI_DEMOGRPAHICS_READER = csv.DictReader(RI_BLOCKDEMOGRAPHICS_CSV)
    for rows in RI_DEMOGRPAHICS_READER:
        csv_tract = rows['TRACT']
        csv_block = rows['BLOCK']
        csv_totpop = rows['TOTAL_POP']
        csv_non_hispanic18 = rows['NON_HISPANIC_OVER_18']
        csv_hispanic18 = rows['HISPANIC_OVER_18']
        csv_whiteover18 = rows['WHITE_OVER_18']
        csv_blackover18 = rows['BLACK_OVER_18']
        csv_americanindianover18 = rows['AMERICAN_INDIAN_OVER_18']
        csv_asianover18 = rows['ASIAN_OVER_18']
        csv_hawaiiover18 = rows['HAWAII_OVER_18']
        csv_otherover18 = rows['OTHER_OVER_18']
        
        properties_dict = {
            'POP100' : int(csv_totpop),
            'NHISOV18' : int(csv_non_hispanic18),
            'HISOV18' : int(csv_hispanic18),
            'WHITEOV18' : int(csv_whiteover18),
            'BLACKOV18' : int(csv_blackover18),
            'AMINOV18' : int(csv_americanindianover18),
            'ASIANOV18' : int(csv_asianover18),
            'HAWOV18' : int(csv_hawaiiover18),
            'OTHEROV18' : int(csv_otherover18)
        }
        
        csv_key = csv_tract+csv_block
        json_feature = CENSUSBLOCK_DICTIONARY[csv_key]
        json_feature.update(properties_dict)

#Create a new file and add the new updated json data to the file.
with open(RI_OUTPUT_FILEPATH, 'w') as RI_OUTPUT_GEOJSON:
    json.dump(CENSUSBLOCK_DATA, RI_OUTPUT_GEOJSON)
    print("Added properties to ri_censusblock")
                            
