# 1.feladat
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from matplotlib import colors
data = pd.read_csv("data.csv")
import plotly.express as px

asd = [sum(list(map(lambda x: 1 if x == "Good" else 0,data['State of Sewage System']))),sum(list(map(lambda x: 1 if x == "Moderate" else 0,data['State of Sewage System']))),sum(list(map(lambda x: 1 if x == "Poor" else 0,data['State of Sewage System'])))]
print(asd)