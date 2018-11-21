#Implements the sklearn CART DTL algorithm

from sklearn import tree
import pandas as pan
import numpy as np
#import graphviz
#import subprocess

df = pan.read_csv("StackOverflow semicolons done satisfaction done 500.csv")
#df = pan.read_csv("test.csv")
#df.drop(columns=[""])
#print(df)
print("Df imported")
print()

Target = df[df.columns[32]]

columns = list(df.columns)

columns.remove('JobSatisfaction')
Features = df[columns]

dt = tree.DecisionTreeClassifier(max_depth=3)#min_samples_split=1000)
dt = dt.fit(Features, Target)
#print(dt)
#with open("dt.dot", 'w') as f:
print("Exporting...")
#dot_data = tree.export_graphviz(dt, out_file=None,feature_names=list(df.columns[:9].values), class_names = ["Yes", "No"])
#print(dot_data)
#graph = graphviz.Source(dot_data) 
#graph.render("ID3")
print("Done")
#exit()
#    command = ["dot", "-Tpng", "dt.dot", "-o", "dt.png"]
#    try:
#        subprocess.check_call(command)
#    except:
#        print("Error")


