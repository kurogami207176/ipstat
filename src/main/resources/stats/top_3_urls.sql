WITH counts AS (
    SELECT url, COUNT(url) as cnt
    FROM logs
    GROUP BY url
)

SELECT url, cnt
FROM counts
ORDER BY cnt DESC, url ASC
LIMIT 3;