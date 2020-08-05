
import pymysql

db = pymysql.connect(host="localhost", user="root", password="Worship1", db="employeeDB")







"""# def print_hi(name):
    Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press ⌘F8 to toggle the breakpoint.


conn = pyodbc.connect('Driver={SQL Server};'
                      'Server=localhost;'
                      'Database=employeeDB;'
                      'Trusted_Connection=yes;')

cursor = conn.cursor()
cursor.execute('SELECT * FROM employeeDB.employee')

for row in cursor:
    print(row)
"""

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
