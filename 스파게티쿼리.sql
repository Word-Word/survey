# ���������� ���� ����� ���ؼ� ����ϴ� ��Ȳ�ε� �ʹ� �������ؼ� �ᱹ �� ���� �� ���� jsp���� ���� ����ؼ� ó�����ֱ�� �ߴ�

SELECT answer, round((total/t2.cnt)*100) AS �ۼ�Ʈ
FROM (
        SELECT answer, COUNT(*) AS total
        FROM surveyresult
        GROUP BY answer
) AS t1, (SELECT count(*)AS cnt
FROM surveyresult) AS  t2
GROUP BY answer
ORDER BY answer ASC