from flask import Flask, render_template
app = Flask("flaskpelda")
#futtatás: flask run, vagy python flask_test.py
#fontos a helyes mappaszerkezet!
@app.get("/") #alap oldalra (index.html) vonatkozó kód
def root():
    flask_title = "Flask oldal"
    lst = [i for i in range(5)]
    #ezeket a változókat fogja átadni a HTML oldal számára!
    return render_template("index.html",
                            page_name = flask_title,
                            nums = lst
                            ) #a kulccsal lehet hivatkozni ott rájuk!
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=1313)