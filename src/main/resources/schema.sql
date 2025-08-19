-- Database: staff_manager_db
-- This script creates a database and tables to manage staff information

-- Delete database if exists
DROP DATABASE IF EXISTS staff_manager_db;

-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS staff_manager_db;

-- Use the newly created database
USE staff_manager_db;

-- -----------------------------------------------------------------------------
-- Table 1: department
-- Stores information about different departments within the company.
-- department_id: Numeric ID for the department (primary key).
-- department_name: Unique name of the department.
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS department (
    department_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, -- Numeric ID for department
    department_name VARCHAR(100) NOT NULL,
    department_description VARCHAR(200) NOT NULL,
    UNIQUE (department_name) -- Ensure department names are unique
);

INSERT INTO department (department_name, department_description) VALUES
('Human Resources', 'Handles employee relations and staffing'),
('Finance', 'Manages financial records and transactions'),
('IT', 'Responsible for technology and systems support'),
('Marketing', 'Oversees marketing strategies and campaigns'),
('Sales', 'Handles sales operations and customer relations');
-- -----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS staff (
    staff_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, -- Numeric ID for staff member
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    social_security_number VARCHAR(20) UNIQUE,
    phone_number VARCHAR(15) UNIQUE,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    date_of_birth DATE,
    join_date DATE,
    department_id INT UNSIGNED NOT NULL,
    -- title_id INT UNSIGNED,
    staff_status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    FOREIGN KEY (department_id) REFERENCES department(department_id) ON DELETE RESTRICT
    -- FOREIGN KEY (title_id) REFERENCES staff_title(title_id) ON DELETE RESTRICT 
);

INSERT INTO staff 
(full_name, email, social_security_number, phone_number, gender, date_of_birth, join_date, department_id, staff_status)
VALUES
('Alice Johnson', 'alice.johnson1@example.com', '340958123789', '0901234567', 'FEMALE', '1990-03-15', '2020-01-10', 1, 'ACTIVE'),
('Bob Smith', 'bob.smith2@example.com', '012934570854', '0901234568', 'MALE', '1985-07-20', '2018-04-05', 2, 'ACTIVE'),
('Clara Williams', 'clara.williams3@example.com', '095218294753', '0901234569', 'FEMALE', '1992-11-05', '2019-09-18', 3, 'INACTIVE'),
('David Brown', 'david.brown4@example.com', '596807399531', '0901234570', 'MALE', '1988-02-25', '2021-03-12', 1, 'ACTIVE'),
('Ella Davis', 'ella.davis5@example.com', '932605103123', '0901234571', 'FEMALE', '1995-06-10', '2020-08-14', 2, 'ACTIVE'),
('Frank Miller', 'frank.miller6@example.com', '108599088387', '0901234572', 'MALE', '1984-12-30', '2017-07-01', 3, 'INACTIVE'),
('Grace Wilson', 'grace.wilson7@example.com', '448970486875', '0901234573', 'FEMALE', '1991-04-18', '2019-11-20', 4, 'ACTIVE'),
('Henry Moore', 'henry.moore8@example.com', '668104644398', '0901234574', 'MALE', '1987-08-07', '2020-05-22', 2, 'ACTIVE'),
('Ivy Taylor', 'ivy.taylor9@example.com', '545340983197', '0901234575', 'FEMALE', '1993-10-14', '2021-01-05', 1, 'ACTIVE'),
('Jack Anderson', 'jack.anderson10@example.com', '156483826926', '0901234576', 'MALE', '1989-01-22', '2018-02-17', 4, 'INACTIVE'),
('Karen Thomas', 'karen.thomas11@example.com', '075385488802', '0901234577', 'FEMALE', '1994-09-11', '2021-06-10', 3, 'ACTIVE'),
('Leo Jackson', 'leo.jackson12@example.com', '733697941060', '0901234578', 'MALE', '1986-03-29', '2019-07-08', 2, 'ACTIVE'),
('Mia White', 'mia.white13@example.com', '991160998818', '0901234579', 'FEMALE', '1990-05-19', '2017-09-25', 4, 'INACTIVE'),
('Noah Harris', 'noah.harris14@example.com', '205940249143', '0901234580', 'MALE', '1992-12-05', '2020-03-15', 1, 'ACTIVE'),
('Olivia Martin', 'olivia.martin15@example.com', '580441558966', '0901234581', 'FEMALE', '1988-07-30', '2018-06-19', 3, 'ACTIVE'),
('Paul Thompson', 'paul.thompson16@example.com', '402627608738', '0901234582', 'MALE', '1985-11-08', '2019-04-28', 2, 'ACTIVE'),
('Quinn Garcia', 'quinn.garcia17@example.com', '274211358046', '0901234583', 'FEMALE', '1993-02-14', '2021-02-12', 4, 'ACTIVE'),
('Ryan Martinez', 'ryan.martinez18@example.com', '782862353371', '0901234584', 'MALE', '1987-05-25', '2020-09-30', 1, 'INACTIVE'),
('Sophia Robinson', 'sophia.robinson19@example.com', '463688857387', '0901234585', 'FEMALE', '1995-08-21', '2018-10-04', 3, 'ACTIVE'),
('Thomas Clark', 'thomas.clark20@example.com', '716673175571', '0901234586', 'MALE', '1991-06-16', '2019-01-11', 2, 'ACTIVE'),
('Uma Lewis', 'uma.lewis21@example.com', '818972626002', '0901234587', 'FEMALE', '1989-09-29', '2017-08-27', 4, 'INACTIVE'),
('Victor Lee', 'victor.lee22@example.com', '388228444149', '0901234588', 'MALE', '1984-10-02', '2021-04-03', 1, 'ACTIVE'),
('Wendy Hall', 'wendy.hall23@example.com', '375126805855', '0901234589', 'FEMALE', '1990-12-15', '2020-11-15', 2, 'ACTIVE'),
('Xavier Allen', 'xavier.allen24@example.com', '725686513818', '0901234590', 'MALE', '1992-07-27', '2019-12-20', 3, 'ACTIVE'),
('Yara Young', 'yara.young25@example.com', '516070571262', '0901234591', 'FEMALE', '1994-01-08', '2018-05-09', 4, 'ACTIVE');

-- CREATE TABLE IF NOT EXISTS staff_title (
--     title_id INT UNSIGNED PRIMARY KEY, -- Numeric ID for job position
--     title_name VARCHAR(100) NOT NULL UNIQUE,
--     title_description VARCHAR(200) NOT NULL UNIQUE
-- );

CREATE TABLE IF NOT EXISTS attendance (
    attendance_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    staff_id INT UNSIGNED NOT NULL,
    work_date DATE NOT NULL,
    check_in_time TIME NOT NULL,
    check_out_time TIME,
    total_hours DECIMAL(5,2),
    -- attendance_status ENUM('REQUESTED', 'APPROVED', 'REJECTED') NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE CASCADE,
    UNIQUE (staff_id, work_date) -- Đảm bảo mỗi nhân viên chỉ có một bản ghi mỗi ngày
    -- có thể add thêm loại công việc như 'FULL_DAY', 'HALF_DAY', 'OVERTIME', 'TRIP' nếu cần
);

CREATE TABLE attendance_request (
    request_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    staff_id INT UNSIGNED NOT NULL,
    request_type ENUM('ADJUSTMENT', 'DAY_OFF', 'OVERTIME') NOT NULL,
    request_status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE CASCADE
);
CREATE TABLE attendance_adjustment_request (
    request_id INT UNSIGNED PRIMARY KEY,
    target_date DATE NOT NULL,
    new_check_in_time DATETIME,
    new_check_out_time DATETIME,
    reason VARCHAR(255),
    FOREIGN KEY (request_id) REFERENCES attendance_request(request_id) ON DELETE CASCADE
);

CREATE TABLE day_off_request (
    request_id INT UNSIGNED PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason VARCHAR(255),
    -- leave_type ENUM('SICK_LEAVE', 'ANNUAL_LEAVE', 'UNPAID_LEAVE') NOT NULL,
    FOREIGN KEY (request_id) REFERENCES attendance_request(request_id) ON DELETE CASCADE
);

CREATE TABLE overtime_request (
    request_id INT UNSIGNED PRIMARY KEY,
    target_date DATE NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    reason VARCHAR(255),
    FOREIGN KEY (request_id) REFERENCES attendance_request(request_id) ON DELETE CASCADE
);
-- CREATE TABLE IF NOT EXISTS Users (
--     user_id INT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE,
--     password_hash VARCHAR(255) NOT NULL,
--     staff_id INT NOT NULL UNIQUE,
--     -- role_id INT NOT NULL,
--     -- FOREIGN KEY (role_id) REFERENCES Roles(role_id) ON DELETE RESTRICT,
--     FOREIGN KEY (staff_id) REFERENCES StaffMembers(staff_id) ON DELETE CASCADE
-- );


-- Optional: Add indexes for frequently queried columns to improve performance
-- CREATE INDEX idx_staff_department ON StaffMembers (department_id);
-- CREATE INDEX idx_staff_job ON StaffMembers (job_id);
-- CREATE INDEX idx_salary_history_staff ON SalaryHistory (staff_id);
-- CREATE INDEX idx_contact_info_staff ON ContactInfo (staff_id);