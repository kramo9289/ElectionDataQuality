import json
import csv

#File paths for all necessary files
NC_CENSUSBLOCK_FILEPATH = 'nc_censusblock.json'
NC_BLOCKDEMOGRAPHICS_FILEPATH = 'nc_blockdemographics.csv'
NC_OUTPUT_FILEPATH = 'complete_nc_censusblock.geojson'

#Open the json containing the censusblocks for RI
with open(NC_CENSUSBLOCK_FILEPATH, 'r') as NC_CENSUSBLOCK_JSON:
    CENSUSBLOCK_DATA = json.load(NC_CENSUSBLOCK_JSON)
    #Create a dictionary to map a tract+block key to the appriopriate location in CENSUSBLOCK_DATA
    CENSUSBLOCK_DICTIONARY = {}
    
    for feature in CENSUSBLOCK_DATA['features']:
        json_blockid= feature['properties']['BLOCKID10']
        CENSUSBLOCK_DICTIONARY[json_blockid] = feature['properties']

#Retrieve the infromation from the csv file that will be injected into the properties of the json file
with open(NC_BLOCKDEMOGRAPHICS_FILEPATH, 'r') as NC_BLOCKDEMOGRAPHICS_CSV:
    NC_DEMOGRPAHICS_READER = csv.DictReader(NC_BLOCKDEMOGRAPHICS_CSV)
    for rows in NC_DEMOGRPAHICS_READER:
        csv_tract = rows['TRACT'].zfill(6)
        csv_block = rows['BLOCK'].zfill(4)
        csv_county = rows['COUNTY'].zfill(3)
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

        nc_fips = "37"
        csv_key = nc_fips + csv_county+csv_tract+csv_block
        json_feature = CENSUSBLOCK_DICTIONARY[csv_key]
        json_feature.update(properties_dict)

#Create a new file and add the new updated json data to the file.
with open(NC_OUTPUT_FILEPATH, 'w') as NC_OUTPUT_GEOJSON:
    json.dump(CENSUSBLOCK_DATA, NC_OUTPUT_GEOJSON)
    print("Added properties to NC_censusblock")
                            

