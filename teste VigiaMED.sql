use vigiamed;

-- unidade
INSERT INTO unidade (nome, endereco, telefone, email)
VALUES ('Unidade Central', 'Av. Principal, 123', '11999998888', 'contato@unidade.com');

-- status disp
INSERT INTO status_dispositivo (estado)
VALUES 
('Ativo'),
('Inativo');

-- tabelalista
INSERT INTO tabela_lista (nome)
VALUES
('Tabela de Pressão'),
('Tabela de Oxigenação'),
('Tabela de Temperatura');

-- usuario
INSERT INTO usuario (nome, tipo, email, senha_hash, unidade_id)
VALUES (
  'Admin Geral',
  'ADMIN',
  'admin@teste.com',
  'HASH123',
  (SELECT id FROM unidade WHERE nome = 'Unidade Central' LIMIT 1)
);

-- log
INSERT INTO log (acao, descricao, data, usuario_id, tabela_list_id)
VALUES (
  'LOGIN',
  'Usuário logou no sistema',
  NOW(),
  (SELECT id FROM usuario WHERE email = 'admin@teste.com' LIMIT 1),
  (SELECT id FROM tabela_lista WHERE nome = 'Tabela de Pressão' LIMIT 1)
);

-- paciente
INSERT INTO patient (nome, referencia)
VALUES
('João da Silva', 'REF001');

-- dispositivo
INSERT INTO dispositivo (modelo, numero_serie, data_aquisicao, status_dispositivo_id, unidade_id)
VALUES (
  'Esfigmo-9000',
  '04CDF8',
  '2024-01-01',
  (SELECT id FROM status_dispositivo WHERE estado = 'Ativo' LIMIT 1),
  (SELECT id FROM unidade WHERE nome = 'Unidade Central' LIMIT 1)
);

-- sensor
INSERT INTO sensor (nome, unidade_medida)
VALUES
('Pressão Arterial', 'mmHg'),
('Oxigenação', '%SpO2'),
('Temperatura Corporal', '°C');