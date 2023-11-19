WITH counts AS (
    SELECT url, COUNT(*) as cnt
    FROM logs
    GROUP BY url
)

SELECT url, cnt
FROM counts
ORDER BY cnt DESC
LIMIT 3;