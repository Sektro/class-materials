from flask import Flask, render_template, request, redirect, url_for
import json
import os

app = Flask(__name__)
DATA_FILE = "data.json"

def load_data():
    if not os.path.exists(DATA_FILE):
        with open(DATA_FILE, "w") as f:
            json.dump({"instructors": [], "courses": [], "students": [], "enrollments": []}, f)
    with open(DATA_FILE, "r") as f:
        return json.load(f)

def save_data(data):
    with open(DATA_FILE, "w") as f:
        json.dump(data, f, indent=4)

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/courses")
def courses():
    data = load_data()
    return render_template("courses.html", courses=data["courses"])

@app.route("/add-course", methods=["GET", "POST"])
def add_course():
    data = load_data()
    if request.method == "POST":
        name = request.form["name"]
        description = request.form["description"]
        instructor_id = int(request.form["instructor"])
        instructor = next((i for i in data["instructors"] if i["id"] == instructor_id), None)
        new_course = {
            "id": len(data["courses"]) + 1,
            "name": name,
            "description": description,
            "instructor": instructor
        }
        data["courses"].append(new_course)
        save_data(data)
        return redirect(url_for("courses"))
    return render_template("add_course.html", instructors=data["instructors"])

@app.route("/edit-course/<int:course_id>", methods=["GET", "POST"])
def edit_course(course_id):
    data = load_data()
    course = next((c for c in data["courses"] if c["id"] == course_id), None)
    if not course:
        return "Kurzus nem található", 404
    if request.method == "POST":
        course["name"] = request.form["name"]
        course["description"] = request.form["description"]
        instructor_id = int(request.form["instructor"])
        instructor = next((i for i in data["instructors"] if i["id"] == instructor_id), None)
        course["instructor"] = instructor
        save_data(data)
        return redirect(url_for("courses"))
    return render_template("edit_course.html", course=course, instructors=data["instructors"])

@app.route("/delete-course/<int:course_id>")
def delete_course(course_id):
    data = load_data()
    data["courses"] = [c for c in data["courses"] if c["id"] != course_id]
    data["enrollments"] = [e for e in data["enrollments"] if e["course_id"] != course_id]
    save_data(data)
    return redirect(url_for("courses"))

@app.route("/students")
def students():
    data = load_data()
    return render_template("students.html", students=data["students"])

@app.route("/add-student", methods=["GET", "POST"])
def add_student():
    data = load_data()
    if request.method == "POST":
        name = request.form["name"]
        email = request.form["email"]
        new_student = {
            "id": len(data["students"]) + 1,
            "name": name,
            "email": email
        }
        data["students"].append(new_student)
        save_data(data)
        return redirect(url_for("students"))
    return render_template("add_student.html")

@app.route("/edit-student/<int:student_id>", methods=["GET", "POST"])
def edit_student(student_id):
    data = load_data()
    student = next((s for s in data["students"] if s["id"] == student_id), None)
    if not student:
        return "Hallgató nem található", 404
    if request.method == "POST":
        student["name"] = request.form["name"]
        student["email"] = request.form["email"]
        save_data(data)
        return redirect(url_for("students"))
    return render_template("edit_student.html", student=student)

@app.route("/delete-student/<int:student_id>")
def delete_student(student_id):
    data = load_data()
    data["students"] = [s for s in data["students"] if s["id"] != student_id]
    data["enrollments"] = [e for e in data["enrollments"] if e["student_id"] != student_id]
    save_data(data)
    return redirect(url_for("students"))

@app.route("/add-instructor", methods=["GET", "POST"])
def add_instructor():
    data = load_data()
    if request.method == "POST":
        name = request.form["name"]
        email = request.form["email"]
        new_instructor = {
            "id": len(data["instructors"]) + 1,
            "name": name,
            "email": email
        }
        data["instructors"].append(new_instructor)
        save_data(data)
        return redirect(url_for("index"))
    return render_template("add_instructor.html")

@app.route("/edit-instructor/<int:instructor_id>", methods=["GET", "POST"])
def edit_instructor(instructor_id):
    data = load_data()
    instructor = next((i for i in data["instructors"] if i["id"] == instructor_id), None)
    if not instructor:
        return "Oktató nem található", 404
    if request.method == "POST":
        instructor["name"] = request.form["name"]
        instructor["email"] = request.form["email"]
        save_data(data)
        return redirect(url_for("index"))
    return render_template("edit_instructor.html", instructor=instructor)

@app.route("/delete-instructor/<int:instructor_id>")
def delete_instructor(instructor_id):
    data = load_data()
    data["instructors"] = [i for i in data["instructors"] if i["id"] != instructor_id]
    for c in data["courses"]:
        if c["instructor"]["id"] == instructor_id:
            c["instructor"] = {"id": 0, "name": "Törölt", "email": ""}
    save_data(data)
    return redirect(url_for("index"))

@app.route("/enroll-student", methods=["GET", "POST"])
def enroll_student():
    data = load_data()
    if request.method == "POST":
        student_id = int(request.form["student_id"])
        course_id = int(request.form["course_id"])
        if not any(e["student_id"] == student_id and e["course_id"] == course_id for e in data["enrollments"]):
            data["enrollments"].append({"student_id": student_id, "course_id": course_id})
            save_data(data)
        return redirect(url_for("students"))
    return render_template("enroll_student.html", students=data["students"], courses=data["courses"])

@app.route("/student-courses/<int:student_id>")
def student_courses(student_id):
    data = load_data()
    student = next((s for s in data["students"] if s["id"] == student_id), None)
    if not student:
        return "Hallgató nem található", 404
    enrolled_course_ids = [e["course_id"] for e in data["enrollments"] if e["student_id"] == student_id]
    enrolled_courses = [c for c in data["courses"] if c["id"] in enrolled_course_ids]
    return render_template("student_courses.html", student=student, courses=enrolled_courses)

if __name__ == "__main__":
    app.run(debug=True)
