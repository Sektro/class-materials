# PyTorch példa mély tanulásra
print('PyTorch példa mély tanulásra:')
import torch
import torch.nn as nn
import torch.optim as optim
import numpy as np
import matplotlib.pyplot as plt

# Példa adatok létrehozása
np.random.seed(42)
x_np = np.random.rand(100, 1).astype(np.float32)
y_np = 4 + 3 * x_np + np.random.randn(100, 1).astype(np.float32)

# Adatok PyTorch tensorokká alakítása
x_tensor = torch.from_numpy(x_np)
y_tensor = torch.from_numpy(y_np)

# Egyszerű neurális hálózat létrehozása
class SimpleNN(nn.Module):
    def __init__(self):
        super(SimpleNN, self).__init__()
        self.linear1 = nn.Linear(1, 10)
        self.relu = nn.ReLU()
        self.linear2 = nn.Linear(10, 1)
    
    def forward(self, x):
        out = self.linear1(x)
        out = self.relu(out)
        out = self.linear2(out)
        return out

model = SimpleNN()

# Veszteségfüggvény és optimalizáló
criterion = nn.MSELoss()
optimizer = optim.Adam(model.parameters(), lr=0.01)

# Modell betanítása
num_epochs = 1000
for epoch in range(num_epochs):
    model.train()
    optimizer.zero_grad()
    outputs = model(x_tensor)
    loss = criterion(outputs, y_tensor)
    loss.backward()
    optimizer.step()

    if (epoch+1) % 100 == 0:
        print(f'Epoch [{epoch+1}/{num_epochs}], Loss: {loss.item():.4f}')

# Előrejelzések készítése
model.eval()
with torch.no_grad():
    y_pred = model(x_tensor)

# Eredmények megjelenítése
plt.scatter(x_np, y_np, color='blue', label='Valós adatok')
plt.plot(x_np, y_pred.numpy(), color='red', label='Előrejelzések')
plt.xlabel('X értékek')
plt.ylabel('Y értékek')
plt.title('Neurális hálózat PyTorch segítségével')
plt.legend()
plt.show()