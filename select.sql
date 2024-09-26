select a.nome, a.id, a.dia, a.mes, a.status, a.email, a.tipo, a.fone from (
    SELECT
        nome_socio1 as nome, cod as id, empresa, dia_nasc1 as dia,
        mes_nasc1 as mes, status, email_soc_1 as email, 1 as tipo,
        concat(ddd1coml,fonecoml1,'/',ddd1cel,celular) as fone
    FROM cliente
    WHERE dia_nasc1 IN ('26','27','28','29','30')
    AND mes_nasc1 LIKE 'julho'
    OR
    dia_nasc1 in ('1')
    AND mes_nasc1 LIKE 'agosto'
    UNION ALL
    SELECT
        nome_soc_2 as nome, cod as id, empresa, dia_nasc2 as dia,
        mes_nasc2 as mes, status, email_soc_1 as email, 2 as tipo,
        concat(ddd2coml,fonecom2,'/',ddd2cel,celular2) as fone
    FROM cliente
    WHERE dia_nasc2 IN ('26','27','28','29','30')
    AND mes_nasc2 LIKE 'julho'
    OR
    dia_nasc2 in ('1')
    AND mes_nasc2 LIKE 'agosto'
) a
where a.status not in (
'DESLIGADA','EXTINTA','PROPONENTE','REGULARIZAÇÃO','SUSPENSA','JURÍDICO,CONJUR','ADVOCACIA/OUTROS','JURÍDICO/INAPTA'
)