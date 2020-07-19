from flask import Flask, jsonify, request #importing Flask class, request object

app = Flask(__name__)
app.config["DEBUG"] = True

def comparator(sanction, entry):
    maxSanc = len(sanction)
    maxEntry = len(entry)
    if (sanction==entry):
        return 100
    elif (len(sanction)<len(entry)):
        a = sanction
        b = entry
        count = 0
        for i in range(len(a)):
            if (a[i]==b[i]):
                count+= 1
    else:
        a = entry
        b = sanction
        count = 0
        for i in range(len(a)):
            if (a[i]==b[i]):
                count+= 1
    percentage = (count/maxSanc) * 100
    return percentage

def Solution(payee):
    import csv
    Sanctions = list()
    with open('sanctionsList.csv', mode='r') as csv_file:
            csv_reader = csv.reader(csv_file, delimiter=',')
            line_count = 0
            for row in csv_reader:
                Sanctions = row
    currentPercentage = 0
    for values in Sanctions:
        currentPercentage = comparator(values, payee)
        if (currentPercentage>= 75):
            return("Hit")
    return("No hit")

@app.route('/', methods=['GET', 'POST']) #allows both GET and POST methods
def form_example():
    if request.method == 'POST':
        payee = request.form.get('payee')
        return Solution(payee);

    return '''<form method="POST">
                Payee Name: <input type="text" name="payee"><br>
                <input type="submit" value="Submit"><br>
                </form>'''
app.run()
