from flask import Flask, jsonify, request, redirect, render_template, url_for #importing Flask class, request object

app = Flask(__name__) #creation of Flask app
app.config["DEBUG"] = True

    #Comparator method compares user's entry to a particular sanctioned String
    #Comparator method returns the percentage similarity in both strings by checking if each characters at each of the Stringss indices are equal
def comparator(sanction, entry):
    maxSanc = len(sanction) #length of Sanctioned String stored
    maxEntry = len(entry) #length of user entry
    sanction = sanction.casefold() #both strings are converted to lower case so that String comparisons are case insensitive
    entry = entry.casefold()
    if (sanction==entry): #inital check to see if Strings are equal
        return 100
    elif (len(sanction)<len(entry)): #if sanctioned string is shorter, the sanctioned String's characters are traversed and compared to user entry. This is done to avoid an out of bounds error.
        a = sanction
        b = entry
        count = 0
        for i in range(len(a)):
            if (a[i]==b[i]):
                count+= 1
    else: #Similarly, if user entry is shorter than sanctioned String, the user entry is traversed and compared to sanctioned string.
        a = entry
        b = sanction
        count = 0
        for i in range(len(a)):
            if (a[i]==b[i]):
                count+= 1 #number of similarities in character is stored in count variable
    percentage = (count/maxSanc) * 100
    return percentage

    #Solution function reads in CSV file storing Sanctioned names
def Solution(payee):
    import csv
    output = "No hit"
    Sanctions = list()
    with open('sanctionsList.csv', mode='r') as csv_file:
            csv_reader = csv.reader(csv_file, delimiter=',')
            line_count = 0
            for row in csv_reader:
                Sanctions = row
    currentPercentage = 0
    #Each value in csv file is compared to user's input and the percentage similarity is returned from comparator method and stored in currentPercentage variable
    for values in Sanctions:
        currentPercentage = comparator(values, payee)
        if (currentPercentage>= 75):
            output = "Hit " + str(currentPercentage) + "%"
            return(output)
    output = "No hit " + str(currentPercentage) + "%"
    return(output)

@app.route('/', methods=['GET', 'POST']) #allows both GET and POST methods
def thisMain():
    if request.method == 'POST':
        entry = request.form.get('entry')
        return render_template('result.html', content = Solution(entry))

    return render_template("entry.html")

app.run()
