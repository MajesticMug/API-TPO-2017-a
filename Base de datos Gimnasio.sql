if not exists(select * from sys.databases where name = 'api_gimnacio')
	create database api_gimnacio;
go

use api_gimnacio
go

create login TP_Gimnasio with password = '123'
go

create user TP_Gimnasio for login TP_Gimnasio
go

EXEC sp_addrolemember 'db_datareader', 'TP_Gimnasio'
go

EXEC sp_addrolemember 'db_datawriter', 'TP_Gimnasio'
go

CREATE TABLE deportes
(
	id_deporte int not null IDENTITY(1,1),
	titulo nvarchar (50) not null,
	descripcion nvarchar (100),	
	activo nvarchar (2) DEFAULT ('Si')
)
alter table deportes
	ADD CONSTRAINT PK_Deporte PRIMARY KEY (id_deporte),
	CONSTRAINT CHK_activoDeporte check (activo in('Si','No'))
	
CREATE TABLE actividades
(
	id_actividad int not null IDENTITY(1,1),
	id_deporte int not null,
	descripcion nvarchar (50) not null,
	activo nvarchar (2) DEFAULT ('Si')
)
alter table actividades
	ADD CONSTRAINT PK_Actividades PRIMARY KEY (id_actividad),
	CONSTRAINT CHK_activoActividad check (activo in('Si','No'))
	
CREATE TABLE clases
(
	id_clase int not null IDENTITY(1,1),
	id_actividad int not null,
	dia nvarchar (10) DEFAULT ('Libre'),
	horaInicio time not null,
	horaFin time not null,
	activo nvarchar (2) DEFAULT ('Si')
)
alter table clases
	ADD CONSTRAINT PK_Clase PRIMARY KEY (id_clase),
	CONSTRAINT CHK_activoClase check (activo in('Si','No')),
	CONSTRAINT CHK_dias check (dia in('Lunes','Martes','Miercoles','Jueves','Viernes','Sabado','Domingo', 'Libre'))

CREATE TABLE abonos
(
	id_abono int not null IDENTITY(1,1),
	abono nvarchar (100) not null,
	inicioVigencia datetime not null,
	periodoPago int not null DEFAULT 1,
	precio decimal (12,2) not null,
	activo nvarchar (2) DEFAULT ('Si')
)
alter table abonos
	ADD CONSTRAINT PK_Abono PRIMARY KEY (id_abono),
	CONSTRAINT CHK_activoAbono check (activo in('Si','No')),
	CONSTRAINT CHK_precio check (precio>=0)

CREATE TABLE abono_actividad
(
	id_abono int not null,
	id_actividad int not null	
)
alter table abono_actividad
	ADD CONSTRAINT PK_Abono_Actividad PRIMARY KEY (id_abono, id_actividad),
	CONSTRAINT FK_Abono_Actividad FOREIGN KEY (id_abono) references abonos (id_abono),
	CONSTRAINT FK_Actividad_Abono FOREIGN KEY (id_actividad) references actividades (id_actividad)


CREATE TABLE escalas_salariales
(
	id_escala int not null IDENTITY(1,1),
	novedades nvarchar (500),
	sueldoBase decimal (12,2) not null,
	sueldoTope decimal (12,2) not null
)
alter table escalas_salariales
	ADD CONSTRAINT PK_Escala_Salarial PRIMARY KEY (id_escala),
	CONSTRAINT CHK_Escala_Base check (sueldoBase>=0),
	CONSTRAINT CHK_Escala_Tope check (sueldoTope>=sueldoBase)

CREATE TABLE empleados
(
	dni nvarchar (8) not null,
	nombre nvarchar (50) not null,
	apellido nvarchar (50) not null,
	domicilio nvarchar (50),
	telefono nvarchar (20),
	id_escala int,
	activo nvarchar (2) DEFAULT ('Si')
)

alter table empleados
	ADD CONSTRAINT PK_Empleado PRIMARY KEY (dni),
	CONSTRAINT CHK_activoEmpleado check (activo in('Si','No')),
	CONSTRAINT FK_Empleado_Escala FOREIGN KEY (id_escala) references escalas_salariales (id_escala)

CREATE TABLE administrativos
(
	dni nvarchar (8) not null,
	puesto nvarchar (50),
	sueldoBasico decimal (12,2) not null,
	deducciones decimal (12,2) not null DEFAULT 0,
	retenciones decimal (12,2) not null DEFAULT 0,
)

alter table administrativos
	ADD CONSTRAINT PK_Empleado_Administrativo PRIMARY KEY (dni),
	CONSTRAINT FK_Empleado_Administrativo FOREIGN KEY (dni) references empleados (dni),
	CONSTRAINT CHK_Administrativo_Sueldo check (sueldoBasico>=0),
	CONSTRAINT CHK_Administrativo_Deducciones check (deducciones>=0),
	CONSTRAINT CHK_Administrativo_Retenciones check (retenciones>=0)

CREATE TABLE profesores
(
	dni nvarchar (8) not null,
)
alter table profesores
	ADD CONSTRAINT PK_Profesor PRIMARY KEY (dni),
	CONSTRAINT FK_Empleado_Profesor FOREIGN KEY (dni) references empleados (dni)
	
CREATE TABLE profesores_fulltime
(
	dni nvarchar (8) not null,
	sueldoBasico decimal (12,2) not null,
	deducciones decimal (12,2) not null DEFAULT 0,
	retenciones decimal (12,2) not null DEFAULT 0,
)

alter table profesores_fulltime
	ADD CONSTRAINT PK_Profesor_FullTime PRIMARY KEY (dni),
	CONSTRAINT FK_Profesor_FullTime FOREIGN KEY (dni) references profesores (dni),
	CONSTRAINT CHK_FullTime_Sueldo check (sueldoBasico>=0),
	CONSTRAINT CHK_FullTime_Deducciones check (deducciones>=0),
	CONSTRAINT CHK_FullTime_Retenciones check (retenciones>=0)

CREATE TABLE profesores_particulares
(
	dni nvarchar (8) not null,
	valorHora decimal (12,2) not null,
	horasTrabajadas int not null DEFAULT 0	
)

alter table profesores_particulares
	ADD CONSTRAINT PK_Profesor_Particular PRIMARY KEY (dni),
	CONSTRAINT FK_Profesor_Particular FOREIGN KEY (dni) references profesores (dni),
	CONSTRAINT CHK_Particular_Valor check (valorHora>=0),
	CONSTRAINT CHK_Particular_Trabajadas check (horasTrabajadas>=0)

CREATE TABLE clase_profesor
(
	id_clase int not null,
	dni nvarchar (8) not null
)
alter table clase_profesor
	ADD CONSTRAINT PK_Clase_Profesor PRIMARY KEY (id_clase, dni),
	CONSTRAINT FK_Profesor_Clase FOREIGN KEY (id_clase) references clases (id_clase),
	CONSTRAINT FK_Clase_Profesor FOREIGN KEY (dni) references profesores (dni)

CREATE TABLE empresas
(
	cuit nvarchar (11) not null,
	razonSocial nvarchar (50) not null,
	activo nvarchar (2) DEFAULT ('Si')
)

alter table empresas
	ADD CONSTRAINT PK_Empresa PRIMARY KEY (cuit),
	CONSTRAINT CHK_Empresa_Activo check (activo in('Si','No'))

CREATE TABLE socios
(
	dni nvarchar (8) not null,
	nombre nvarchar (50) not null,
	apellido nvarchar (50) not null,
	domicilio nvarchar (50),
	telefono nvarchar (20),
	mail nvarchar (50),	
	cuit nvarchar (11) null,
	activo nvarchar (2) DEFAULT ('Si')
)

alter table socios
	ADD CONSTRAINT PK_Socio PRIMARY KEY (dni),
	CONSTRAINT FK_Socio_Empresa FOREIGN KEY (cuit) references empresas (cuit),
	CONSTRAINT CHK_Socio_Activo check (activo in('Si','No'))


CREATE TABLE aptos_medicos
(
	id_apto int not null IDENTITY(1,1),
	dni nvarchar (8) not null,
	fechaCertificado datetime not null,
	profesionalFirmante nvarchar (50) not null,
	observaciones nvarchar (500)
	--El estado del apto medico es un campo calculado
)
alter table aptos_medicos
	ADD CONSTRAINT PK_Apto PRIMARY KEY (id_apto),
	CONSTRAINT FK_Apto_Socio FOREIGN KEY (dni) references socios (dni)

CREATE TABLE ingresos
(
	dni nvarchar (8) not null,
	fechaIngreso datetime not null,
	aceptado nvarchar (2) not null
)
alter table ingresos
	ADD CONSTRAINT PK_Ingreso PRIMARY KEY (dni, fechaIngreso),
	CONSTRAINT FK_Ingreso_Socio FOREIGN KEY (dni) references socios (dni),
	CONSTRAINT CHK_Ingreso_Aceptado check (aceptado in('Si','No'))

CREATE TABLE recibos_sueldos
(
	id_recibo int not null IDENTITY(1,1),
	dni nvarchar (8) not null,
	fechaPago datetime not null,
	sueldo decimal (12,2) not null
)

alter table recibos_sueldos
	ADD CONSTRAINT PK_Recibo PRIMARY KEY (id_recibo),
	CONSTRAINT FK_Recibo_Empleado FOREIGN KEY (dni) references empleados (dni)

CREATE TABLE inscripciones
(
	id_inscripcion int not null IDENTITY(1,1),
	id_abono int not null,
	proxVencimiento datetime,
	ultimoPago datetime,
	vencido nvarchar (2) DEFAULT ('No')
)

alter table inscripciones
	ADD CONSTRAINT PK_Inscripcion PRIMARY KEY (id_inscripcion),
	CONSTRAINT CHK_Inscripcion_Vencida check (vencido in('Si','No')),
	CONSTRAINT FK_Inscripcion_Abono FOREIGN KEY (id_abono) references abonos (id_abono)

CREATE TABLE corporativas
(
	id_inscripcion int not null,
	cuit nvarchar (11) not null,
	inicioVigencia datetime not null DEFAULT GETDATE(),
	finVigencia datetime,
	porc_descuento decimal (4,2) not null,
)

alter table corporativas
	ADD CONSTRAINT PK_Inscripcion_Corporativa PRIMARY KEY (id_inscripcion),
	CONSTRAINT FK_Inscripcion_Corporativa FOREIGN KEY (id_inscripcion) references inscripciones (id_inscripcion),
	CONSTRAINT FK_Corporativa_Empresa FOREIGN KEY (cuit) references empresas (cuit),
	CONSTRAINT CHK_Corporativa_Descuento check (porc_descuento >=0)

CREATE TABLE inscripciones_normales
(
	id_inscripcion int not null,
	dni nvarchar (8) not null,
	fechaInscripcion datetime not null DEFAULT GETDATE(),
	id_corporativa int null
)

alter table inscripciones_normales
	ADD CONSTRAINT PK_Inscripcion_Normal PRIMARY KEY (id_inscripcion),
	CONSTRAINT FK_Inscripcion_Normal FOREIGN KEY (id_inscripcion) references inscripciones (id_inscripcion),
	CONSTRAINT FK_Inscripcion_Convenio FOREIGN KEY (id_corporativa) references corporativas (id_inscripcion),
	CONSTRAINT FK_Inscripcion_Socio FOREIGN KEY (dni) references socios (dni)

