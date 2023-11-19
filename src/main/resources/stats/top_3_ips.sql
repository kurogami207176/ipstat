WITH counts AS (
    SELECT ip, COUNT(*) as cnt
    FROM logs
    GROUP BY ip
)

SELECT ip, cnt
FROM counts
ORDER BY cnt DESC
LIMIT 3;