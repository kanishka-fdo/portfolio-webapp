CREATE TABLE projects (
                          id BIGINT IDENTITY(1,1) PRIMARY KEY,
                          title VARCHAR(200) NOT NULL,
                          short_description VARCHAR(500) NOT NULL,
                          description VARCHAR(MAX) NULL,
                          tech_stack VARCHAR(300) NULL,
                          github_url VARCHAR(300) NULL,
                          live_url VARCHAR(300) NULL,
                          created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

CREATE TABLE contact_messages (
                                  id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                  name VARCHAR(120) NOT NULL,
                                  email VARCHAR(180) NOT NULL,
                                  subject VARCHAR(200) NOT NULL,
                                  message VARCHAR(MAX) NOT NULL,
                                  created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

INSERT INTO projects (title, short_description, description, tech_stack, github_url, live_url)
VALUES
    ('Portfolio Website', 'Personal portfolio with admin dashboard', 'Spring Boot + SQL Server + Thymeleaf project', 'Spring Boot, SQL Server, Thymeleaf', NULL, NULL);