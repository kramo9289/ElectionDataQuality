import csv

csv_filepath = 'election_table.csv'
output_filepath = 'identified_errors.csv'

csv_columns = ['id', 'error_type']
csv_dict = []
added_precincts = []

with open(csv_filepath, 'r') as csvfile:
    csv_reader = csv.DictReader(csvfile)
    for rows in csv_reader:
        totvot = rows['tot']
        precinct_id = rows['precinct_id']
        if(totvot == '0' and precinct_id not in added_precincts):
            row_dict = {}
            row_dict['id'] = precinct_id
            row_dict['error_type'] = 'VOTANOM'
            csv_dict.append(row_dict)
            added_precincts.append(precinct_id)

with open(output_filepath, 'w',  newline = '') as csvfile:
    write = csv.DictWriter(csvfile, fieldnames = csv_columns)
    write.writeheader()
    for row in csv_dict:
        write.writerow(row)

    print('exported voting errors')


            
    
