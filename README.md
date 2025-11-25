# 🎓 Smart Student Information & Performance Track  

A **Console-Based Student Management System** built using **Core Java & SQLite**, demonstrating **Object-Oriented Programming (OOP)**, **Database Integration (JDBC)**, **Collections**, **Exception Handling**, and a **menu-driven console interface**.  

Designed as a **University-Level Java Project** for academic submission and practical understanding of **OOP + JDBC + Modular Architecture**.  

---

# 🏅 Badges  
![Java](https://img.shields.io/badge/Java-Programming-orange)  
![SQLite](https://img.shields.io/badge/SQLite-Database-blue)  
![Status](https://img.shields.io/badge/Project-Active-brightgreen)  
![License](https://img.shields.io/badge/License-Open--Source-success)  
![Design](https://img.shields.io/badge/Design-Modular-lightgrey)  
![Interface](https://img.shields.io/badge/UI-Console--Menu-yellow)  

---

# 🚀 Features  

### 👨‍🎓 Student Management  
- Add new student (auto-generated unique ID)  
- Update student details (name, age, course, roll no.)  
- Delete student record  
- Search student by ID, Roll Number, or Name  
- Display all students  

### 📊 Performance Tracking  
- Add subject marks  
- Auto-calculate **Total, Percentage, Grade**  
- Grade scale:  
  - ≥90 → A+  
  - ≥80 → A  
  - ≥70 → B+  
  - ≥60 → B  
  - ≥50 → C  
  - <50 → F  

### 🕒 Attendance Management  
- Track present vs total days  
- Auto-calculate attendance percentage  
- Attendance warning if <75%  

### 🏆 Ranking & Reports  
- Generate **Top Performers List**  
- Overall ranking based on marks + attendance  
- Summary report:  
  - Total students  
  - Pass/Fail count  
  - Class average percentage  
  - Highest scorer  
  - Attendance analysis  

### 🧩 Additional Functionalities  
- Auto-create SQLite database (`students.db`)  
- Input validation & error handling  
- Clean modular OOP structure  
- Persistent storage across sessions  
- Result Card display for each student  

---

# 🛠️ Technologies Used  

| Component        | Technology            |  
|------------------|----------------------|  
| **Language**     | Java (Core Java)     |  
| **Database**     | SQLite               |  
| **Connectivity** | JDBC                 |  
| **Data Structures** | HashMap, ArrayList, Objects |  
| **IDE (Optional)** | VS Code / IntelliJ / Eclipse |  
| **Design**       | Menu-driven console interface |  

---

# 📁 Project Structure 

SmartStudentProject/ │ ├── src/ │   ├── Main.java           → Entry point, menu & user input │   ├── Student.java        → Student model class │   ├── Performance.java    → Marks, percentage & grade logic │   ├── StudentManager.java → Business logic (CRUD, ranking, reports) │   ├── DatabaseHelper.java → SQLite DB connection & queries │ ├── lib/ │   └── sqlite-jdbc.jar     → SQLite JDBC driver │ ├── students.db             → Auto-created SQLite database └── README.md               → Documentation


---

# 🗄️ Database Details  

- Database auto-generated: **students.db**  
- No manual table creation required  
- Tables used:  
  - **students** → ID, name, age, course, roll  
  - **subjects** → subject list  
  - **marks** → student marks per subject  
  - **attendance** → present/total days  

Database is created automatically when program runs for the first time.  

---

# ▶️ How to Run  

### **Step 1 — Install Java JDK**  
Check installation:  
```bash
java -version  
javac -version

Step 2 — Compile

Windows:
javac -cp ".;lib/sqlite-jdbc.jar" src/*.java

Linux/Mac:
javac -cp ".:lib/sqlite-jdbc.jar" src/*.java

Step 3 — Run

Windows:
java -cp ".;lib/sqlite-jdbc.jar" src.Main


Linux/Mac:
java -cp ".:lib/sqlite-jdbc.jar" src.Main


✔ Database will be auto-created
✔ Program will start in console

💡 Future Enhancements
- GUI-based version using JavaFX/Swing
- Export reports to PDF/CSV
- Multi-user login (Admin + Faculty)
- Cloud database support
- Subject credit system (GPA calculation)
- Role-based access control

🤝 Author
Mr. Raj Ali
B.Tech – Computer Science Engineering(AI)
Galgotias Uiversity
GitHub: Rajali07
