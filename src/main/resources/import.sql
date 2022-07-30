INSERT INTO public.tb_students(	id, birth_date, name, sex) VALUES (1, '1994-02-21', 'Gabriel Bahia', 'MEN');
	
INSERT INTO public.tb_students(	id, birth_date, name, sex) VALUES (2, '1998-02-21', 'Bahia Dev', 'MEN');
	
INSERT INTO public.tb_students(	id, birth_date, name, sex) VALUES (3, '2000-12-21', 'João Miguel', 'MEN');
	
INSERT INTO public.tb_students(	id, birth_date, name, sex) VALUES (4, '1999-06-10', 'Hinata', 'WOMEN');



INSERT INTO public.tb_contact(	id, email, instagram, number_phone, id_aluno) VALUES (1, 'gab.arteinfo@gmail.com', '@GabrielBahia.code', '75988069416', 1);
	
INSERT INTO public.tb_contact(	id, email, instagram, number_phone, id_aluno) VALUES (2, 'email@gmail.com', '@AlgumDado', '75988069416', 2);


INSERT INTO public.tb_monthly_payment(	id, days_late_payment, status, form_of_payment, monthlyfee, payment_date, id_student) VALUES (1, 0, 'IN_DAY', 'MONEY', 39.99, '2022-07-01',1);
	
INSERT INTO public.tb_monthly_payment(	id, days_late_payment, status, form_of_payment, monthlyfee, payment_date, id_student) VALUES (2, 0, 'IN_DAY', 'MONEY', 39.99, '2022-06-05',1);
	
INSERT INTO public.tb_monthly_payment(	id, days_late_payment, status, form_of_payment, monthlyfee, payment_date, id_student) VALUES (3, 0, 'LATE', 'MONEY', 39.99, '2022-05-05',2);


INSERT INTO public.tb_measurements(	id, biceps, breastplate, height, period, shoulder, triceps, waist, weight, id_aluno) VALUES (1, 30.0, 90.0, 1.75,'2022-06-01', 60.0, 25.0, 30.0, 75.0, 1);
	
INSERT INTO public.tb_measurements(	id, biceps, breastplate, height, period, shoulder, triceps, waist, weight, id_aluno) VALUES (2, 23.0, 90.0, 1.65, '2022-05-01', 60.0, 20.0, 30.0, 75.0, 2);

INSERT INTO public.tb_measurements(	id, biceps, breastplate, height, period, shoulder, triceps, waist, weight, id_aluno) VALUES (3, 24.3, 93.0, 1.65,'2022-07-01', 65.0, 25.0, 33.0, 78.0, 1);
