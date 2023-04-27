import json
import csv

NC_PRECINCT_FILEPATH = 'north_carolina_result.json'
NC_2016_FILEPATH = 'results_pct_2016.txt'
NC_2018_FILEPATH = 'results_pct_2018.txt'
NC_OUTPUT_FILEPATH = 'complete_nc_precinct.geojson'
NC_REMOVE_PROPERTIES = ['COUNTY_ID','COUNTY_NAM','ENR_DESC', 'OF_PREC_ID',
                        'PREC_ID']
PRECINCT_UPDATED_DATA = None
PRECINCT_DICTIONARY = {}
COUNTY_DICTIONARY = {}

def main():
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY
    global COUNTY_DICTIONARY

    properties_dict = {'totvot16':0, 'totvot18':0, 'totvotpres':0,
                       'repvot16':0, 'repvot18':0, 'repvotpres':0,
                       'demvot16':0, 'demvot18':0, 'demvotpres':0,
                       'othvot16':0, 'othvot18':0, 'othvotpres':0,}

    cong_dict = {}
    cong_dict['districtid']='2'
        
    with open(NC_PRECINCT_FILEPATH, 'r') as PRECINCT_JSON:
        PRECINCT_UPDATED_DATA = json.load(PRECINCT_JSON)
        for feature in PRECINCT_UPDATED_DATA['features']:
            feature['properties'].update(properties_dict)
            json_county = feature['properties']['COUNTY_NAM']
            json_precinct = feature['properties']['PREC_ID']


            if(json_county == 'WAKE'):
                if(json_precinct == '19-18' or json_precinct == '19-19' or
                   json_precinct == '16-10' or json_precinct == '16-11'):
                    feature['properties'].update(cong_dict)
                
            
            #Use the voting disctrict(precinct) unique identifier as a key to save the location of the geospatial data for that specific precinct
            PRECINCT_DICTIONARY[json_county + json_precinct] = feature['properties']
            
    electionDataScript(NC_2016_FILEPATH, False)
    electionDataScript(NC_2018_FILEPATH, True)
    removePropertiesFromJson(NC_REMOVE_PROPERTIES)
    
    #Create a new file and add the new updated json data to the file
    with open(NC_OUTPUT_FILEPATH, 'w') as OUTPUT_GEOJSON:
        json.dump(PRECINCT_UPDATED_DATA, OUTPUT_GEOJSON)
        print("Added properties to output file: " +  NC_OUTPUT_FILEPATH)
    
def removePropertiesFromJson(properties):
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY
    for feature in PRECINCT_UPDATED_DATA['features']:
        for prop in properties:
            del feature['properties'][prop]

def convertMonthToNumber(code):
    months = { "jan": "01", "feb":"02", "mar": "03","apr": "04", "may":"05", "jun": "06","jul": "07", "aug":"08", "sep": "09","oct": "10", "nov":"11", "dec": "12"}
    monthday = code.split("-")
    codetoreturn = ""
    if(len(monthday) !=2):
        return "skippre"
    if(monthday[0].lower() in months.keys()):
        if(len(monthday[1]) != 2):
            codetoreturn= months[monthday[0].lower()] + "-0" + monthday[1]
        else:
            codetoreturn= months[monthday[0].lower()] + "-" + monthday[1]
    elif(monthday[1].lower() in months.keys()):
        if(len(monthday[0]) != 2):
            codetoreturn = months[monthday[1].lower()] + "-0" + monthday[0]
        else:
            codetoreturn= months[monthday[1].lower()] + "-" + monthday[0]
    else:
        codetoreturn = monthday[0] +"-"+monthday[1]
        
    return codetoreturn

def electionDataScript(ELECTION_FILEPATH, IS_2018):
    global PRECINCT_UPDATED_DATA
    global PRECINCT_DICTIONARY
    global COUNTY_DICTIONARY

    electionfile = open(ELECTION_FILEPATH, 'r')
    for line in electionfile:
        properties_dict = {}
        electionarray = line.split("  ")[0].split("\t")
        if(len(electionarray) < 12):
            continue
        county_name = electionarray[0]
        
        if('CABARRUS' in county_name.upper()):
            precinct_code = convertMonthToNumber(electionarray[2])
        else:
            precinct_code = electionarray[2]
            
        contest_type = electionarray[5]
        party_code = electionarray[7].lower()
        total_votes = electionarray[13]

        if(party_code !='rep' and party_code != 'dem'):
            party_code = 'oth'
        properties_dict['name'] = county_name + ' ' + precinct_code
        properties_dict['statefp'] = '37'

        dictionary_key = county_name + precinct_code
        
        if(dictionary_key in PRECINCT_DICTIONARY.keys()):
            json_feature = PRECINCT_DICTIONARY[dictionary_key]
            properties_dict['countyname'] = json_feature['COUNTY_NAM'].lower()
            properties_dict['origname'] = json_feature['ENR_DESC']

            
            
            if('US HOUSE OF REPRESENTATIVES' in contest_type and not IS_2018):
                district_id = contest_type.split(' ')[-1]
                properties_dict['districtid'] = district_id
                json_feature[party_code + 'vot16'] = json_feature[party_code + 'vot16'] + int(total_votes)
                json_feature['totvot16'] = json_feature['totvot16'] + int(total_votes)
            if('US PRESIDENT' in contest_type):
                json_feature[party_code + 'votpres'] = json_feature[party_code + 'votpres'] + int(total_votes)
                json_feature['totvotpres'] = json_feature['totvotpres'] + int(total_votes)
            if('US HOUSE OF REPRESENTATIVES' in contest_type and IS_2018):
                json_feature[party_code + 'vot18'] = json_feature[party_code + 'vot18'] + int(total_votes)
                json_feature['totvot18'] = json_feature['totvot18'] + int(total_votes)

            json_feature.update(properties_dict)
        
if __name__ == "__main__":
    main()
                            
