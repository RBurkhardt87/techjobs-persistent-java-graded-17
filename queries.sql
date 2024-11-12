--Part 1: List columns and their data types job_seq

-- > id		 : int PK
-- > employer : VARCHAR(225)
-- > name	 : VARCHAR(225)
-- > skills	 : VARCHAR(225)



--Part 2: write a query to list the names of the employers in St. Louis City.
          -- Do NOT specify an ordering for the query

SELECT name
FROM employer
WHERE location = "St. Louis City";



--Part 3:  write the SQL statement to remove the job table.

DROP TABLE job;



--Part 4: write a query to return the names of all skills that are attached to jobs in alphabetical order.
          -- If a skill does not have a job listed, it should not be included in the results of this query.

-- This is how I personally did it, but test didn't approve. It's because I don't have a WHERE statement and I am
-- only returning the name instead of all the columns
SELECT skill.name
FROM job_skills
INNER JOIN skill ON job_skills.skills_id = skill.id
ORDER BY name ASC;

-- So this is the query setup to meet test expectations:

SELECT * FROM skill
INNER JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.jobs_id IS NOT NULL
ORDER BY name ASC;

