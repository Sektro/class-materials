from flask import Flask, jsonify, render_template
app = Flask(__name__)
users = [                                                                                                       # Teszt adatok 
    {"id": 1, "name": "Anna", "email": "anna@example.com"},                      
    {"id": 2, "name": "Béla", "email": "bela@example.com"},
    {"id": 3, "name": "Cecil", "email": "cecil@example.com"}
]
@app.route("/api/users")                      # JSON API végpont
def get_users():
    return jsonify(users)                       
@app.route("/users")                          # HTML oldal megjelenítése
def user_list():
    return render_template("users.html", users=users)
@app.route("/")                                            # Főoldal
def home():
    return "<h2>Üdv a Flask alkalmazásban!</h2><p>Menj a /users vagy /api/users oldalra.</p>"
if __name__ == "__main__":
    app.run(debug=True)
