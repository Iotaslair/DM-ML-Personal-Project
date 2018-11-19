#Implements the sklearn CART DTL algorithm

from sklearn import tree
import pandas as pan
import numpy as np
import graphviz
import subprocess

df = pan.read_csv("PreProcessedDataCartNoNeighbourhood.csv")
#df = pan.read_csv("test.csv")
#df.drop(columns=[""])
#print(df)
print("Df imported")

Target = df[df.columns[9]]
#Features = df[("PatientID", "Gender", "AppointmentDay", "Age", "Neighbourhood",
#            "Scholarship", "Hipertension", "Diabetes", "Alcoholism", "Handcap", 
#            "SMS_received")]
Features = df[list(df.columns[:9])]

dt = tree.DecisionTreeClassifier(min_samples_split=1000)
dt = dt.fit(Features, Target)
#print(dt)
#with open("dt.dot", 'w') as f:
print("Exporting...")
dot_data = tree.export_graphviz(dt, out_file=None,
                                feature_names=list(df.columns[:9].values),
                                class_names = ["Yes", "No"])
#print(dot_data)
graph = graphviz.Source(dot_data) 
graph.render("CARTOut")
print("Done")
#exit()
#    command = ["dot", "-Tpng", "dt.dot", "-o", "dt.png"]
#    try:
#        subprocess.check_call(command)
#    except:
#        print("Error")


