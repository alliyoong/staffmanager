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

-- CREATE TABLE IF NOT EXISTS staff_title (
--     title_id INT UNSIGNED PRIMARY KEY, -- Numeric ID for job position
--     title_name VARCHAR(100) NOT NULL UNIQUE,
--     title_description VARCHAR(200) NOT NULL UNIQUE
-- );

-- CREATE TABLE IF NOT EXISTS attendance (
--     attendance_id INT UNSIGNED PRIMARY KEY, 
--     staff_id INT UNSIGNED NOT NULL,
--     work_date DATE NOT NULL,
--     check_in_time DATETIME NOT NULL,
--     check_out_time DATETIME NOT NULL,
--     FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE CASCADE,
--     UNIQUE (staff_id, work_date) -- Đảm bảo mỗi nhân viên chỉ có một bản ghi mỗi ngày
-- );

-- CREATE TABLE IF NOT EXISTS attendance_detail (
--     detail_id INT PRIMARY KEY AUTO_INCREMENT,
--     attendance_id INT UNSIGNED NOT NULL, 
--     staff_id INT UNSIGNED NOT NULL,
--     reason VARCHAR(255),
--     FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE CASCADE,
--     FOREIGN KEY (attendance_id) REFERENCES attendance(attendance_id) ON DELETE CASCADE,
--     UNIQUE (staff_id, work_date) -- Đảm bảo mỗi nhân viên chỉ có một bản ghi mỗi ngày
-- );
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