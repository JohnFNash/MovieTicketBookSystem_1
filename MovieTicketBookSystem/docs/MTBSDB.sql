create database MTBSDB

go


create table TB_USERINFO	--�û���
(
	USER_ID		int		primary key		identity(10000,1),		--�û����
	ACCOUNT		varchar(30)		not null	unique,				--�˺�
	PASSWORD	char(32)		not null,				--����
	HEAD_PATH	varchar(50)		not null default('images/head/default_head.jpg'),						--ͷ��·��
	IS_ADMIN	bit	not null	default(0),				--(0��ʾ����ͨ�û���1��ʾ������Ա)
	IS_EXIST	bit not null	default(1)				--(�û��Ƿ񻹴��ڣ�1���ǣ�0������)
)

insert into TB_USERINFO values('aa','aa',default,0,default)
insert into TB_USERINFO values('bb','bb','images/head/h001.jpg',0,default)
insert into TB_USERINFO values('cc','cc','images/head/h002.jpg',0,default)
insert into TB_USERINFO values('dd','dd',default,1,default)

select * from TB_USERINFO


create table TB_AREA		--������
(
	AREA_ID int not null primary key,			--������
	AREA_NAME varchar(30) not null,				--������������
	PARENT_ID int not null,						--�������
	AREA_LEVEL tinyint not null,				--���򼶱�(1ʡ/2��/3����)
	[STATUS] bit not null default(1),			--״̬(1����/0������)
	FIRST_LETTER char(1) not null default(' '),	--ƴ������ĸ(����ֱϽ��/2��������)
	IS_HOT bit not null default(0)				--�������Ƿ�����(1 ����/2 ������)
)

select * from TB_AREA



create table TB_CINEMA		--��ӰԺ��
(
	CINEMA_ID	int		primary key		identity(10000,1),						--ӰԺ���
	CINEMA_NAME	varchar(50)		not null,										--ӰԺ����
	--DISTRICT_NAME	varchar(20)	not null,										--����������
	AREA_ID int foreign key references TB_AREA(AREA_ID),						--���������
	ADDRESS		varchar(100)	not null,										--ӰԺ��ַ
	LOGO	varchar(50)	not null default('images/cinema/default_logo.png'),		--ӰԺlogo
	SCENE	varchar(50)		 not null,											--ӰԺʵ��
	TEL		varchar(15)		 not null,											--ӰԺ�绰
	SPECIAL		varchar(255),													--ӰԺ��ɫ
	DESCRIPTION		varchar(1000),												--ӰԺ����
	VISIT_COUNT		bigint 	not null	default(0),								--ӰԺ��ע
	IS_OPEN			bit		not null	default(1)								--(1��ʾ������Ӫҵ��0��ʾ��δӪҵ)	
)

insert into TB_CINEMA values('����UME����Ӱ��-������',500105,'�������������8�ű�����ֹ���㳡B��5F(5¥�Ϲ�B��,4¥�¹�A��)','images/cinema/c001.jpg',
'images/cinema/UME_movie001.jpg','023-67701166','��ɽ���׼Ҿ�������֤�����Ǽ�Ӱ�ǣ�ռ�����7000��ƽ�ף�����ʮ�����ӳ������Ŀǰ���ڲ��ɶ���Ķ����ִ���Ӱ�ǡ���',
'UME�������Ӱ�������UMEӰԺ���ẕ̇������Ϻ�����Ӱ�Ǻ���2004��12��Ͷ���˽��ĵ�����Ӱ�ǣ�Ҳ��ɽ���׼Ҿ�������֤�����Ǽ�Ӱ�ǣ�ռ�����7000��ƽ�ף�����ʮ�����ӳ����
��Ŀǰ���ڲ��ɶ���Ķ����ִ���Ӱ�ǡ� ÿ����ӳʮ�ಿ��Ƭ����ӳһ�ٶೡ�λ���������ѡ��ġ���Ӱ���С�֮����������������������Ӱ��Ϊ�����״��� ����UME����Ӱ�ǣ�������
ƽ�沼��ͼ������һ�к����г���ʮ������������򣬴���Ʊ������Ʊ��˳�ų���120�׵ĳ������ν���1��10��ӳ��������������¥ͨ������ͨ�����ɽ����¹�11����15��ӳ���� 
���˵��߳�������Ӱ��߹����⿧�������¹ݻ�������������Ӱ���͵�Ӱ��ɵ�ȫ���״���ȫ����ʩ��',210979,1)
insert into TB_CINEMA values('������ݹ���Ӱ��-IMAX��',500108,'�ϰ������ϴ��28�Ų��н�Э���ǹ�ʱ���㳡L2-L3��','images/cinema/c002.jpg','images/cinema/scene_jinyi.jpg','023-62627755',
'�����ϵ�һ���Ļ��������ϵ����ߵ���Ӱ���ռ���������ϵ�һĻ�հס���','������ݹ��ʵ�Ӱ���ǹ�ʱ������Ϊ���ݽ���Ӱ�Ӵ�ý�ɷ����޹�˾������ǧ�������й�������׼�IMAXӰ�ǣ��������ϵ�һ���Ļ��
������ϵ����ߵ���Ӱ���ռ���������ϵ�һĻ�հס� ������ݹ��ʵ�Ӱ���ǹ�ʱ���꣬�����׼�IMAX����Ӱ�ǣ������ڿƼ���������ʢ�硣�ܽ��������6000 ƽ���ף�Ӱ��ӵ��8������Ӱ������λ��1700������
ͬʱӵ�����ϵ�����һ��Ļ��IMAX3DӰ����4��˫����������',139748,1 )
insert into TB_CINEMA values('�������Ӱ��-���ڳǵ�',500105,'���������±�·8�Ŵ��ڳ���ҵ����6-7¥','images/cinema/c005.jpg','images/cinema/scene_wanda.jpg','023-62906088',
'��Ӫ�����ᡢʱ�С���Լ��񣬽���Ϊ�����˵�Ӱ�Ļ����ѡ����ʵ���ѡ��������','����������Ӱ�Ǵ��ڳǵ�λ�ڽ��������Ŵ��ڳ�6-7¥�������Ժ��������Ͷ���˽��ĵڶ��Ҷ���Ӱ�ǡ�Ӱ�ǹ���13��Ӱ����
�ܼ�Լ1600����ϯ�����ڳǵ갴���������Ǽ�ӰԺ��׼���н��裬�Ҳ�����Ƹм�ǿ�ĺ��ִ���Ʒ��Ӫ�����ᡢʱ�С���Լ��񣬽���Ϊ�����˵�Ӱ�Ļ����ѡ����ʵ���ѡ������
����������Ӱ�Ǵ��ڳǵ�ȫ���������ַ�ӳ�������з�ӳ�������豸�����ù��ʶ���Ʒ�ƣ���ӳ�豸ȫ��ʵ�����ֻ�������������ӰԺ����ϵͳ��TMS�������ַ�ӳ��������������Ʒ�ƣ�
���ӹ�˾���Ϳɹ�˾ӰԺ���ַ�ӳ��ϵ�в�Ʒ�����ֲ��ŷ��������öűȹ�˾��GDC��˾��Ʒ�����������豸����������������������Ʒ�ƶűȹ�˾CP650\CP750ϵ�в�Ʒ��DTS��˾��Ʒ��
ӰԺ����ϵͳ������������Ʒ��JBL���䡢�ʹڹ��š�QSC����͹��ţ�3D��ӳϵͳ��',155783,1 )
insert into TB_CINEMA values('���컪���ֵ�Ӱ��-Ԭ�Ҹڵ�',500107,'��������Ԭ�Ҹڰ���·1�����³��ϳ�3¥','images/cinema/c003.jpg','images/cinema/scene_huayi.jpg','023-62626670',
'�������ֵ�ӰԺԬ�Ҹڵ�����������Ƚ���˫��3D�����������ڴ���ˮ��������������3D���ʡ��ڷ�ӳ���棬���ù����Ƚ���ӰԺ�Զ���ӳ����ϵͳ����','�����ֵ�ӰԺԬ�Ҹڵ꾭Ӫ�����6000ƽ���ס�
ӵ��8��Ӱ�������а���1��˫��3DӰ����2����������1����������1�������Ϣ��������915����λ���ӷ�ӳ���������ĵ���Ļ�����Σ�ȫ�����ù����������豸��������Ϊ��Ӱ�ߴ����Ʒ�ʵĹ�Ӱ������
�����¡����ӽǡ����ο���Ļ���ù���ȫ��λ���ܵ�Ӱ���������ܸ�ˮ׼�������ռ䣬����Ч�����ˣ����Դ����緶����ӵ������ͷ�Ȳյ�2�����������1��˽����������������
���ں��˸���ʱ�г�����������Ԫ�أ����Գ�С�������������ɡ�һ����ӰԺ��8���������ķ�ӳ���������ӰԺ��Ӱ�����ı��һ�񡣴��⣬�����ֵ�ӰԺԬ�Ҹڵ�����������Ƚ���˫��3D������
�����ڴ���ˮ��������������3D���ʡ��ڷ�ӳ���棬���ù����Ƚ���ӰԺ�Զ���ӳ����ϵͳ�����ֻ��Զ����ơ�',71573,1 )
insert into TB_CINEMA values('����Ӱ�ǣ�����ͻ��꣩',500106,'ɳƺ������Ͽ�㳡���н�6�Żͻ��¼�Ԫ����㳡8¥','images/cinema/c006.jpg','images/cinema/scene_hengdian.jpg','023-65007645',
'��Ӱ�ǰ��չ������Ǽ�ӰԺ��߱�׼��ơ����죬װ�޺�������ʩ�ߵ�������һ������Ӱ���ʣ����������������ߵ�Ӱ�ǡ���','����ͻ�����Ӱ����һ�����Ǽ������๦��Ӱ�ǣ�Ӱ�ǰ��չ������Ǽ�ӰԺ��
�߱�׼��ơ����죬װ�޺�������ʩ�ߵ�������һ������Ӱ���ʣ����������������ߵ�Ӱ�ǡ�Ӱ�������4000ƽ���ף�����9��һ���Ĺ��ʱ�׼רҵӰ����1������VIP����������1000�������ڡ�10�����ʱ�׼Ӱ
�������˵�����������Ϊ�ߵ��������豸������JBL���ź����죬Ӣ��DOLBY���ֽ��������¹����ڸ�������ӳ��ͷ��Ӣ�������˹�߷�������Ļ��һ���������豸��һ��������Ч��������������δ�й��������𺳡�
�ḻ�������ó��������ʵķ�����Ӳ�����ã���Ϊ��������ҵ��λ�ṩ��Ӱ�����顢���Ρ����Ȼ����ͬʱҲ��Ϊ��Ʒ�ơ���Ʒ�ṩ������չʾ��ƽ̨��',78544,1 )

select * from TB_CINEMA


create table TB_SELLER		--�̼ұ�
(
	SELLER_ID	int		primary key		identity(10000,1),			--�̼ұ��
	ACCOUNT		varchar(30)		not null	unique,					--�̼��˺�
	PASSWORD	char(32)		not null,							--�̼�����
--	CINEMA_ID	int			unique	references TB_CINEMA(CINEMA_ID),	--��ӰԺ���
	IS_EXIST	bit		not null	default(1),			--�̼��Ƿ���ڣ�1���ǣ�0:���ǣ�	
)
insert into TB_SELLER values('aaa','aaaaaa',default)
insert into TB_SELLER values('bbb','bbbbbb',default)
insert into TB_SELLER values('ccc','cccccc',default)
insert into TB_SELLER values('ddd','dddddd',default)
insert into TB_SELLER values('eee','eeeeee',default)

select * from TB_SELLER


create table TB_SELLER_CINEMA		--�̼�-ӰԺ������Ϣ��
(
	SELLER_ID int foreign key references TB_SELLER(SELLER_ID),		--�̼ұ��
	CINEMA_ID int foreign key references TB_CINEMA(CINEMA_ID)		--ӰԺ���
)
insert into TB_SELLER_CINEMA values(10000, 10000)
insert into TB_SELLER_CINEMA values(10001, 10001)
insert into TB_SELLER_CINEMA values(10002, 10002)
insert into TB_SELLER_CINEMA values(10003, 10003)
insert into TB_SELLER_CINEMA values(10004, 10004)

select * from TB_SELLER_CINEMA



create table TB_ACTOR		--��Ա/���ݱ�
(
	ACTOR_ID		int		primary key		identity(10000,1),		--��Ա/���ݱ��
	ACTOR_NAME	varchar(50)		not null							--��Ա����
)

insert into TB_ACTOR values('�ź���')
insert into TB_ACTOR values('���һ�')
insert into TB_ACTOR values('�ָ���')
insert into TB_ACTOR values('١���')
insert into TB_ACTOR values('����')
insert into TB_ACTOR values('����')
insert into TB_ACTOR values('�º�')
insert into TB_ACTOR values('��ӱ')
insert into TB_ACTOR values('��³һ')
insert into TB_ACTOR values('���')
insert into TB_ACTOR values('����')
insert into TB_ACTOR values('������')
insert into TB_ACTOR values('�����')
insert into TB_ACTOR values('١��Ϊ')
insert into TB_ACTOR values('����ǿ')
insert into TB_ACTOR values('����')
insert into TB_ACTOR values('��־��')
insert into TB_ACTOR values('������')
insert into TB_ACTOR values('���')
insert into TB_ACTOR values('��ͤ��')
insert into TB_ACTOR values('����÷')
insert into TB_ACTOR values('֣����')
insert into TB_ACTOR values('��贼�')
insert into TB_ACTOR values('����˳')
insert into TB_ACTOR values('����')
insert into TB_ACTOR values('����')
insert into TB_ACTOR values('������')
insert into TB_ACTOR values('�����')
insert into TB_ACTOR values('�°���')
insert into TB_ACTOR values('¹��')
insert into TB_ACTOR values('������')
insert into TB_ACTOR values('���')
insert into TB_ACTOR values('��Ӣ')
insert into TB_ACTOR values('��־��')
insert into TB_ACTOR values('���')
insert into TB_ACTOR values('����˳')

select * from TB_ACTOR




create	table TB_MOVIE		--ӰƬ��
(
	MOVIE_ID	int		primary key		identity(10000,1),			--ӰƬ���
	NAME	varchar(50)		not null,								--ӰƬ����
	POST	varchar(50)		not null,								--ӰƬ����
	--DIRECTOR_ID		int		references TB_ACTOR(ACTOR_ID),			--���ݱ��  
	TITLE	varchar(50),											--ӰƬ����
	IS_3D	bit	not null	default(0),							--�Ƿ�Ϊ3D(0��ʾ�����ǣ�1��ʾ����)
	AREA	varchar(50)	not null		default('�й�'),		    --ӰƬ����
	DURATION	tinyint	not null,									--ʱ������λ�����ӣ�
	[LANGUAGE]	varchar(50)	not null,								--����
	PUBLISH		datetime	not null,								--��ӳʱ��
	[DESCRIPTION]	varchar(1000)	not null,							--������
	LIKE_COUNT	int		not null	default(0),						--ϲ����Ŀ
	ATTENTION_COUNT	int		not null	default(0),					--��ע��Ŀ
	BUY_COUNT	int		not null	default(0),						--��Ʊ��Ŀ		
	IS_EXIST	bit not null	default(1)							--(ӰƬ�Ƿ񻹴��ڣ�1���ǣ�0������)			
)

insert into TB_MOVIE values('��ȡ����ɽ','images/movie/mbig001.jpg','"���������Ϊ��Կ��Ō�ը�조',1,default,141,'����','2014-12-23',
'   1947�궬��������������203С�ֶ����׳��ٽ������ָ��� �Σ��Ĵ����£����ϼ��������˻���Ⱶ��ֺ�ѩԭ�������հ�ȫ�����Ա�����٣��ź��� �Σ�
������Ա���㣨١��� �Σ����߳�Ԯ��Ϊ�����߽�����������������ִ��������װǱ����ѡ�����ɽ�������������ؿ�������������ױ��й�����������ɽ���Ϊ������ɽ�Ͼš���
	������һ����˴���������һ������Ϊɽ��ս�Ѵ����鱨����ɽկ�е�һ������Ů��ȴ���Ž�������������������203С�ֶ����ܵ��������縱����ƽ�������ѣ�
������������կ���������ٵ�����ʡ�����ɽ�����һ� �� ���ٳ����ټ��硱�ϣ���������203С�ֶӵ�ս����ӭ���˽˷����������ʱ����һ����ս�������⡭��',
275759, 1324944, 918322, default)
insert into TB_MOVIE values('΢��֮����Ѿ�','images/movie/mbig002.jpg','��������һ�����£�������һ��ʱ�С���',0,default,98,'����','2014-12-24',
'    һ������΢��ɳ�����º� �Σ�����΢��Ѱ��һ����ϵİ��飬����ȴ��ʱ�д���Ů��������ӱ �Σ��յ�ӹ�׵�һҹ���ϣ���ɳ��ʼ�ռ���:������һ�����£�
������һ��ʱ�С�����ǧ�ذ���, ������΢����Ů��Ȼ����̨ɽ������·��ꡭ��',
88293,403909,379372, default)
insert into TB_MOVIE values('һ��֮ң','images/movie/mbig003.jpg','��Ҫô������Ҫô������',1,default,140,'����','2014-12-18',
'   ������䣬����ħ����������ѡ�����ݱ�������������භ�ޣ�������Ŀ��ȫ�ǿ�,������ͳ����Ӣ����� �Σ��������Σ���ѡ�ٰ��������գ����� �Σ�����������¡�
Ȼ��һ��ıɱ�߸���һ�У�����Ӣ��ɥ��Ȫ���������������ģ����Ͳٰ��ѡ����һ�����С�������� �Σ���Ī��֮����Ϊ�����޵У��������������������� �Σ��ĳ��֣�
�ı��������˵����ˣ�ħ��˫��չ�������Ծ�������ð��һ����������������һ��֮ң����',
217134,1133990,690618, default)
insert into TB_MOVIE values('����','images/movie/mbig004.jpg',null,0,default,90,'����','2014-12-31',
'   20����30������粨���ܵ��Ϻ������ǰ�ɫ �ֲ�����ʱ�ڣ��й����µ�С�Ķ�����ش���š�һ�����ܻ�����Ϊ��ͽ�ĳ��������ƻ��������ֳ��ܱߵ���Ϥ��������
���µ����������壨���һ� �Σ�ͻΧ��Ҫ��ϧһ�д���Ѹ����֯Ӫ�ȣ�ԭ��1��������ơ�ǡ�ڱ��������С�������Ѷ��ϲȴ�����޷���������ġ����ơ������Ƕ��ø��ֲ��̼��̣�
��ͼ ��������������Ů�ԡ�����Сѧ��ʦ���Ա�ޱ����־�� �Σ��������ʻ����սݣ����� �Σ�������¥�ϰ����ϼ�ң���ͤ�� �Σ�������ѧ���ڡ�ʩ��ʦ����� �Σ��Լ�żȻ��ǣ���¼�
�еġ�Ůѧ����С����˿����˳������ơ������࡭�����������Ƴ��ˡ����ơ���ʬ�壬���������Ƶ�������Ե��
    ����һ��ʧ�ܵ�Ӫ�ȣ���˭й¶�ˡ����ơ�����ʵ��ݣ�ֱ����ź��Ա�ޱ����18����һ�죬�����ڹ�������������֤��ȫ���Ʒ���Ҳ��˽����ٴ�����������������ϻ���������Ŀ��̻��䡭�� ',
8027, 57359,22426, default)
insert into TB_MOVIE values('�ط�20��','images/movie/mbig005.jpg','"˭�����������ϻ�ͯ�����Σ�"',0,default,131,'����','2015-01-08',
'   70������������ξ��������� �Σ�����˼�����Ϊ20��������Ů������������� �Σ���������ݻص��ճ�����������Լ������Ӵ����ֶӣ�
��������һϵ����Ц�ԷǵĹ��£�����ʰ�������룬���ٴ����˰��飬�Ͳ�ͬ����ļ�λ��ʿ�����������ܼ�̷�������°��� �Σ����ֶӼ����ָ�����ǰ����¹�� �Σ���
�������ξ�����үү��󺣣�����˳ �Σ����津�硣',
49085,370470,212536, default)

select * from TB_MOVIE 



create table TB_DIRECTOR			--���ݱ�
(
	DIRECTOR_ID int not null primary key identity(10000, 1),	--���ݱ��
	DIRECTOR_NAME varchar(50) not null							--��������
)
insert into TB_DIRECTOR values('���')
insert into TB_DIRECTOR values('�˳���')
insert into TB_DIRECTOR values('����')
insert into TB_DIRECTOR values('������')
insert into TB_DIRECTOR values('������')

select * from TB_DIRECTOR



create table TB_MOVIE_DIRECTOR				--ӰƬ-������Ϣ������
(
	MOVIE_ID int foreign key references TB_MOVIE(MOVIE_ID),			--ӰƬ���
	DIRECTOR_ID int foreign key references TB_DIRECTOR(DIRECTOR_ID)	--���ݱ��
	unique(MOVIE_ID, DIRECTOR_ID)
)

insert into TB_MOVIE_DIRECTOR values(10000, 10000)
insert into TB_MOVIE_DIRECTOR values(10001, 10001)
insert into TB_MOVIE_DIRECTOR values(10002, 10002)
insert into TB_MOVIE_DIRECTOR values(10003, 10003)
insert into TB_MOVIE_DIRECTOR values(10004, 10004)

select * from TB_MOVIE_DIRECTOR


 
create table TB_MOVIE_ACTOR	--ӰƬ-��Ա��
(
	MOVIE_ID		int				references TB_MOVIE(MOVIE_ID),		--ӰƬ���
	ACTOR_ID		int				references TB_ACTOR(ACTOR_ID),		--��Ա���
	primary key (MOVIE_ID,ACTOR_ID)
)
insert into TB_MOVIE_ACTOR values(10000,10001)
insert into TB_MOVIE_ACTOR values(10000,10002)
insert into TB_MOVIE_ACTOR values(10000,10003)
insert into TB_MOVIE_ACTOR values(10000,10004)
insert into TB_MOVIE_ACTOR values(10000,10005)
insert into TB_MOVIE_ACTOR values(10000,10006)

insert into TB_MOVIE_ACTOR values(10001,10008)
insert into TB_MOVIE_ACTOR values(10001,10009)
insert into TB_MOVIE_ACTOR values(10001,10010)
insert into TB_MOVIE_ACTOR values(10001,10011)
insert into TB_MOVIE_ACTOR values(10001,10012)
insert into TB_MOVIE_ACTOR values(10001,10013)
insert into TB_MOVIE_ACTOR values(10001,10014)

insert into TB_MOVIE_ACTOR values(10002,10027)
insert into TB_MOVIE_ACTOR values(10002,10028)
insert into TB_MOVIE_ACTOR values(10002,10029)
insert into TB_MOVIE_ACTOR values(10002,10037)
insert into TB_MOVIE_ACTOR values(10002,10017)
insert into TB_MOVIE_ACTOR values(10002,10036)
insert into TB_MOVIE_ACTOR values(10002,10038)
insert into TB_MOVIE_ACTOR values(10002,10039)
insert into TB_MOVIE_ACTOR values(10002,10030)

insert into TB_MOVIE_ACTOR values(10003,10018)
insert into TB_MOVIE_ACTOR values(10003,10019)
insert into TB_MOVIE_ACTOR values(10003,10020)
insert into TB_MOVIE_ACTOR values(10003,10021)
insert into TB_MOVIE_ACTOR values(10003,10022)
insert into TB_MOVIE_ACTOR values(10003,10023)
insert into TB_MOVIE_ACTOR values(10003,10024)
insert into TB_MOVIE_ACTOR values(10003,10025)
insert into TB_MOVIE_ACTOR values(10003,10026)

insert into TB_MOVIE_ACTOR values(10004,10032)
insert into TB_MOVIE_ACTOR values(10003,10033)
insert into TB_MOVIE_ACTOR values(10003,10034)
insert into TB_MOVIE_ACTOR values(10003,10035)
insert into TB_MOVIE_ACTOR values(10003,10040)

select * from TB_MOVIE_ACTOR




create table TB_MOVIE_TYPE	--ӰƬ-���ͱ�
(
	MOVIE_ID		int			references TB_MOVIE(MOVIE_ID),						--ӰƬ���
	TYPE_NAME	varchar(10)	not null,												--���ͱ��
	primary key (MOVIE_ID,TYPE_NAME)
)
insert into TB_MOVIE_TYPE values(10000,'ս��')
insert into TB_MOVIE_TYPE values(10000,'����')

insert into TB_MOVIE_TYPE values(10001,'����')

insert into TB_MOVIE_TYPE values(10002,'����')
insert into TB_MOVIE_TYPE values(10002,'ϲ��')


insert into TB_MOVIE_TYPE values(10003,'����')
insert into TB_MOVIE_TYPE values(10003,'����')
insert into TB_MOVIE_TYPE values(10003,'ս��')

insert into TB_MOVIE_TYPE values(10004,'����')
insert into TB_MOVIE_TYPE values(10004,'ϲ��')

select * from TB_MOVIE_TYPE



create table TB_CINEMA_REMARK		--ӰԺ���۱�
(
	ID		int		primary key  identity(10000,1),			--���۱��
	CINEMA_ID	int		references TB_CINEMA(CINEMA_ID),	--��ӰԺ���
	[USER_ID]	int		references TB_USERINFO(USER_ID),--�û����
	CONTENT		varchar(150)	not null,				--��������
	GRADE		decimal(2,1)	default(0.0),				--�÷�
	[TIME]		datetime	not null,				--����ʱ��
	LIKE_COUNT	int		not null	default(0),		--������
	IS_EXIST 	bit		not null	default(1),		--�����Ƿ���ڣ�1����/0������
	STATUS		tinyint		not null	default(1)		--���۵�״̬��1��δ��ˣ�2���ͨ����3���δͨ��
)
insert into TB_CINEMA_REMARK values(10000,10000,'��UME�Ǿ��Եİ�����ӰԺ�����쿪ҵ�����ڣ����ӰԺ��û��֮һ�� ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10000,10001,'�����ã���ͨ���㣬�Ժ�����һ������Ӱ��Ч���𺳣�֧��֧�֣��� ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10000,10002,'������ȥ�ĵ�ӰԺ��3D���������ĵ���3D��ҹ���ں����ģ���ȫ���ŵ���λ�úá� ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10000,10003,'���������ܱ������룬���Ƿ���Ա�����һ�㣬�е�����Ϊ�ǵĸо��� ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10000,10003,'��������ѡ��࣬��ƬҲ�ǳ�����װ�޺�����Ӳ��һ�����ؼ��Ǿ�����������DMAX��Ļ�������𺳣�Ч���ܸܵ� ��IMAX�������£����۸�ȴ��IMAXʵ�ݵö�~ �� ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10000,10001,'����ӰԺ������λ�úõ㡣�����Ʊ�۹󡣹ؼ��Ƿ���Ա̬�ȼ�����ӣ������϶������Դ������ǱƲ������ǲ���ȥ��ҵġ� ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10001,10000,'�Խ����Ǿ��Եİ�����ӰԺ�����쿪ҵ�����ڣ����ӰԺ��û��֮һ�� ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10001,10001,'�����ã���ͨ���㣬�Ժ�����һ������Ӱ��Ч���𺳣�֧��֧�֣��� ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10001,10002,'������ȥ�ĵ�ӰԺ��3D���������ĵ���3D��ҹ���ں����ģ���ȫ���ŵ���λ�úá� ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10001,10003,'���������ܱ������룬���Ƿ���Ա�����һ�㣬�е�����Ϊ�ǵĸо��� ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10001,10003,'��������ѡ��࣬��ƬҲ�ǳ�����װ�޺�����Ӳ��һ�����ؼ��Ǿ�����������DMAX��Ļ�������𺳣�Ч���ܸܵ� ��IMAX�������£����۸�ȴ��IMAXʵ�ݵö�~ �� ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10001,10001,'����ӰԺ������λ�úõ㡣�����Ʊ�۹󡣹ؼ��Ƿ���Ա̬�ȼ�����ӣ������϶������Դ������ǱƲ������ǲ���ȥ��ҵġ� ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10002,10000,'�Ի����Ǿ��Եİ�����ӰԺ�����쿪ҵ�����ڣ����ӰԺ��û��֮һ�� ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10002,10001,'�����ã���ͨ���㣬�Ժ�����һ������Ӱ��Ч���𺳣�֧��֧�֣��� ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10002,10002,'������ȥ�ĵ�ӰԺ��3D���������ĵ���3D��ҹ���ں����ģ���ȫ���ŵ���λ�úá� ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10002,10003,'���������ܱ������룬���Ƿ���Ա�����һ�㣬�е�����Ϊ�ǵĸо��� ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10002,10003,'��������ѡ��࣬��ƬҲ�ǳ�����װ�޺�����Ӳ��һ�����ؼ��Ǿ�����������DMAX��Ļ�������𺳣�Ч���ܸܵ� ��IMAX�������£����۸�ȴ��IMAXʵ�ݵö�~ �� ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10002,10001,'����ӰԺ������λ�úõ㡣�����Ʊ�۹󡣹ؼ��Ƿ���Ա̬�ȼ�����ӣ������϶������Դ������ǱƲ������ǲ���ȥ��ҵġ� ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10003,10000,'������Ǿ��Եİ�����ӰԺ�����쿪ҵ�����ڣ����ӰԺ��û��֮һ�� ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10003,10001,'�����ã���ͨ���㣬�Ժ�����һ������Ӱ��Ч���𺳣�֧��֧�֣��� ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10003,10002,'������ȥ�ĵ�ӰԺ��3D���������ĵ���3D��ҹ���ں����ģ���ȫ���ŵ���λ�úá� ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10003,10003,'���������ܱ������룬���Ƿ���Ա�����һ�㣬�е�����Ϊ�ǵĸо��� ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10003,10003,'��������ѡ��࣬��ƬҲ�ǳ�����װ�޺�����Ӳ��һ�����ؼ��Ǿ�����������DMAX��Ļ�������𺳣�Ч���ܸܵ� ��IMAX�������£����۸�ȴ��IMAXʵ�ݵö�~ �� ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10003,10001,'����ӰԺ������λ�úõ㡣�����Ʊ�۹󡣹ؼ��Ƿ���Ա̬�ȼ�����ӣ������϶������Դ������ǱƲ������ǲ���ȥ��ҵġ� ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10004,10000,'�Ժ���Ǿ��Եİ�����ӰԺ�����쿪ҵ�����ڣ����ӰԺ��û��֮һ�� ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10004,10001,'�����ã���ͨ���㣬�Ժ�����һ������Ӱ��Ч���𺳣�֧��֧�֣��� ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10004,10002,'������ȥ�ĵ�ӰԺ��3D���������ĵ���3D��ҹ���ں����ģ���ȫ���ŵ���λ�úá� ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10004,10003,'���������ܱ������룬���Ƿ���Ա�����һ�㣬�е�����Ϊ�ǵĸо��� ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10004,10003,'��������ѡ��࣬��ƬҲ�ǳ�����װ�޺�����Ӳ��һ�����ؼ��Ǿ�����������DMAX��Ļ�������𺳣�Ч���ܸܵ� ��IMAX�������£����۸�ȴ��IMAXʵ�ݵö�~ �� ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10004,10001,'����ӰԺ������λ�úõ㡣�����Ʊ�۹󡣹ؼ��Ƿ���Ա̬�ȼ�����ӣ������϶������Դ������ǱƲ������ǲ���ȥ��ҵġ� ',5.0,'2014-12-28 20:25',0,default,default)

select * from TB_CINEMA_REMARK



create table TB_CR_TREEPATHS	--ӰԺ��������Ϣ���ӹ�ϵ��
(
	ANCESTOR int not null foreign key references TB_CINEMA_REMARK(ID),	--�����۱��
	DESCENDANT int not null foreign key references TB_CINEMA_REMARK(ID)	--�����۱��
	primary key (ANCESTOR, DESCENDANT)
)




create table TB_MOVIE_REMARK		--ӰƬ���۱�
(
	ID	int		primary key  identity(10000,1),			--���۱��
	MOVIE_ID		int		references TB_MOVIE(MOVIE_ID),	--ӰƬ���
	[USER_ID]	int		references TB_USERINFO(USER_ID),	--�û����
	TITLE		varchar(50)		not null default('Ӱ��'),	--Ӱ������
	CONTENT		varchar(3000)	not null,				--��������
	GRADE		decimal(2,1)	not null default(7.0),			--�÷�
	[TIME]	datetime	not null,					--����ʱ��
	LIKE_COUNT	int		not null	default(0),		--������
	IS_EXIST 	bit		not null	default(1),		--�����Ƿ���ڣ�1����/0������
	STATUS		tinyint		not null	default(1)		--���۵�״̬��1��δ��ˣ�2���ͨ����3���δͨ��
)

insert into TB_MOVIE_REMARK values(10000,10000,default,'Сʱ��Ϳ�������С˵���ֺ�ѩԭ���ı�ĵ��Ӿ磬�Ǹ�ʱ�����軹���ҽ��ܣ�
����һ���͸о��ܰ����о��ݵĺ��棬�ر���������ɽ�Դ������ɱ���ᣬ���������ô��������·���ѩ��������������������ǣ����ź���ʹ��
�޲����Լ��������ʮ����ſ��������˷ˣ����ǿ������˲����׺�һ���ͬʱҲ�ܿ��������Ϳ�**��ˮ��Ĺ������쵼�Ľ�ž��Ŀɰ���
ϣ���ⲿӰƬ������ҵĲ�ֻ��3DЧ���������ʴ̼���Ҳ�����Ǽǵø���ǰ����Ϊ�˺�����ǵ���������������Ѫ������
����������������֮���ף��ú������ϧ����˺����ڡ�',8.5,'2014-12-24 12:20',0,default,default)
insert into TB_MOVIE_REMARK values(10000,10001,'�ֺ����,�����е�ս����','����ܶ�������ڲ������������ˣ������������ˡ�����Ϸ����
��ν������Ϸ������ȷ��պ�ż������������ʶ��ָ̬��ͽ����������������ʽ������ħ��������Ϸ��Ҳ�������񻯡�����Ϸ��һ������ȡ��Ʃ�硶��ȡ����ɽ��
�����ֺ�ѩԭ��������Ϊһ�������������Ʒ������й�С˵��Ϸ�磨Ϸ��������Ӱ�����Ӿ硢����Ƭ�ȶ����ͬ���ı���ò���˷��������3D��ĺ����Ƭ��
Ҳ����ʱ�µ�ӰȦ���еġ���IP����������ְ�ȥ�Ķ������ܸ��ڵ�ӰԺ��ţ�������ȡ����ɽ������',8.0,'2014-12-25 14:23',0,default,default)
insert into TB_MOVIE_REMARK values(10000,10002,'�о��ܺÿ�','�о��ܺÿ��о��ܺÿ��о��ܺÿ��о��ܺÿ�',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10000,10003,'�����ŶΣ�����ʮ�㣡','����һ��ʼ�뿴�ⲿ��Ӱ�ǳ����ָ���ȥ�ģ���Ϊ��ϲ��������ڹ�͹����ٽ�ɫ���ܲ���
Ψ��û�������ݽ�����ս��Ƭ��������ν�ɫ��ת���������ҳ������飻��Σ����Ϲֵĵ�Ӱ���ö�˵�����������ǿ�ģ������ٲ����ſ����Ч�ͱ���Ķ�����
����ڱ�Ƭ�����ֵ���Ϊͻ����������������ڷ��棬�����о������������ȡ����ɽ��ʷ�Ŷ���Ϊ����Ϊ��������֪������ǹ�ڴ򶷵�ս��Ϸ���̺������������飬
����ԱС�׸������йᴩ��˨�Ӹ��ǣ�Ҳ��Ҫ˵�������ͣ���Ҳ�Ǹ������в���ȱ�ٵ�һ���֣����ģ���Ա���ݵľ�����ǿ���������������û��˵���ź��衢���һԡ�
�ָ��¡�١��櫡����ж��Ǻܰ�����Ա����󣬾��Ǳ�Ƭ������ɫ��һ�㣬�����Ķ������˷��ԣ����������Ǹ������ʰ������û����Ļ�����ڵĽ��ͣ������ǲ����˺Ǻǡ�
������������������о����Ǻܺõģ����Կڱ�����Ҳ�Ͳ���Ϊ���ˣ�',8.0,'2014-12-26 18:23',5,default,default)

insert into TB_MOVIE_REMARK values(10001,10000,'�о��ܺÿ�','�о��ܺÿ��о��ܺÿ��о��ܺÿ��о��ܺÿ�',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10001,10001,'һ��׬Ǯ��һ����Ǯ��','������ĵ����������ö���Щ���Σ������������ʱ����ӡ�Ĵ����ߣ��ٳ��޷����Լ���ʱ�����߳���������׷ױ������ͬ־��ʲô����ͬ־����ʵ�޹����䣬����������������ַŲ�ƽ��̬���Ų��ͼ��ӣ�ƫҪ��Ϊ�Լ���ʱ��������·��',7.0,'2014-12-26 18:23',2)
insert into TB_MOVIE_REMARK values(10001,10002,default,'���ꡰ΢��֮����Ѿ���������Ī���ĸж�����һ����дӰ���ĳ嶯��ɳ����Ϊһ��С��磬
Ͷ�ʷ�������ǰ����һ����һ�������Ϊ������Ͷ�ʷ��ĸ���Ҫ�󣬵�ȻҲ��Ϊ���Լ������룬һ�̲���ͣ���޸��Լ��ľ籾���ӹ��ֲ�룬�ĵ�С������
�ָĵ���㤣����º����Ĵ�����...Ͷ�ʷ��ļ���Ҳ��������ɳ����û��ʱ��Է�˯������û��ʱ��̸���ѣ���󼸽���Ϊ���Լ������������ĵ����±��ϼ���ţ�
�����...ͻȻ��Ͷ�ʷ�Ϊ�˸��������ת�ְѱ����˱��ˣ����е������Ŭ����һҹ�仯Ϊ��Ӱ��������һ�����У�û�����ں����С�������
������ζҲֻ��ɳ���Լ����ˡ�������Ҫ�У����ǿ�ʼ���ܴ���̫������Խ�󣬳ű��Ŀ�����Խ�󣬽���Ѿ�������õġ������׸����������벢����Ϊ֮��ֵ��ˡ�',7.0,'2014-12-28 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10001,10000,'��΢�����������ذ�OR�����ذ�','�ⲿ��Ӱ�����֮���ǌ�˿׷��Ů��Ĺ��¡���˿�������꿴�ƺܼ���
ȴ����һ��ִ��׷��������·�ĸ߹�֮�ģ����������İ����Ů��İ�һ����ִ�š��ᶨ����ϡ��ɰ������ְ������ջ����۽��������ģ�������Ƥ�����ļ����İ�
�����������������������ֵ���𾴵ģ���δ�����Լ����ºյı��ݿ�Ȧ�ɵ㣬֮ǰ���ڡ��Ҵ����꡷���Ҿ��þ���һ����������ɵȱ����Ա��û�뵽����ϲ���츳�����������ģ�
��εı��ָ�Ϊ������֡����쾡�¡����룬���ֿ���·�˵Ĵ��峤���ܾ߱���˿����ԣ�Ҳֻ���츳���Խ����ˡ�',7.5,'2014-12-28 19:23',0,default,default)

insert into TB_MOVIE_REMARK values(10002,10000,'�о��ܺÿ�','�о��ܺÿ��о��ܺÿ��о��ܺÿ��о��ܺÿ�',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10002,10001,'����ʱ�գ�һ��֮ң','����ˢ������ġ�һ��֮ң��Խ��ĥһֱ������ζ�ܾá�һ�飬λ����ƫ��
�Ա߻�����С��Ŀ�ⲻ�������꼶��ע����Ϸ��Ϸ��ֻ��һ���־硣�ɲ����ģ���������������һ��UA���ŵ�imax�������Ӵ�������һ׮�ĵ׻���
������Ŀ����һֱ��һ�����̼��ߵ�Ů�ӣ���ʵ��ˡ���Ȼ�ڹ�Ӱǰ������һ���Ƿ�ͳ�������Ԥ���롣һ�ж��ˣ���Ȥ�����ˣ����ľ��úÿ������ú��˷ܡ�
��֮ǰ��Щ��Ϊ�Լ���������ϲ������û�о��ſڿ�������Ƭ������û�й��������������֡������վ��ǲ�Ը�������Ӣ˵�Ұ��㣬����Ҳ�������Թ���˵�������֡�',6.0,'2014-12-22 18:23',0,default,default)
insert into TB_MOVIE_REMARK values(10002,10000,'�о��ܺÿ�','�о��ܺÿ��о��ܺÿ��о��ܺÿ��о��ܺÿ�',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10002,10000,'�о��ܺÿ�','�����Ӱ�������Ѿ����ܣ����˽����Լ�Ϊһ��֮ң���Ľ��ͣ������Ե�Ӱ����Ķ��������˽��ˡ�
�����ַ��ͽ��ĵ�����Ĭ���������ۣ������Ӿ��ϻ�������Щ����Ҳ��һЦ��ֻ��ͻȻ��ӰƬ��ӳ�Ķ����������ˣ����֡�����ֻ�뿴���Լ��뿴���Ķ�����������֪����ʵ���������ĸо���
�����������Ķ����ɡ�',8.0,'2014-12-29 18:23',0,default,default)


insert into TB_MOVIE_REMARK values(10003,10000,'�о��ܺÿ�','�о��ܺÿ��о��ܺÿ��о��ܺÿ��о��ܺÿ�',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10003,10001,default,'��ҹ���꣬��ֵƱ�ۡ���������Ͽ����͹��ˡ�����˳��Ϊ��һ���ɾ��Ǹ����͵ġ�����**�Եײ�����
�����е��ƣ��ͷ�����ȫ����磬��������Ҳ����һ���¡�5��ץ��ȥ�ģ�ѧ���Ƕ�������**�Ե�ȥ��¥ͨ�籨�ŵģ���ĸ��Ϊ�˾�Ů����У���������Ƴ����ˡ�
־����**�Ե׵����ˣ��������У���������ð��������������û�ϡ�',7.0,'2015-01-02 18:23',0,default,default)
insert into TB_MOVIE_REMARK values(10003,10002,'�о�������','�η����м���ʦ���賡Ů������ͽ���򱻲�ʿ�߷���
֤��Ϊ��ʿ���Ե�һ�͵ڶ���֤�ݣ�����֤��ΪС��ĸ����������ʿ����������־����ݽ�ɫʱ��ʼӦ��˵��������ͽ������
����־����ݽ�ɫ�ڱ�����������ʱ���ﺦ�£�����ͽӦ�����ڽ�ź�Ϊ�з�Ǳ����������������˵���������ڼ�����
�Ҹ���־����ݽ�ɫ���Ǵӱ������Ȼ����ģ�����ͽӦ�ò�֪��������˭����֪�����������ǹ�����Ա',7.0,'2015-01-03 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10003,10003,'�о��ܺÿ�','�о��ܺÿ��о��ܺÿ��о��ܺÿ��о��ܺÿ�',8.0,'2015-01-04 18:23',2,default,default)

insert into TB_MOVIE_REMARK values(10004,10000,'���ط��������','���Եľ�ϲ֮�������ſ��������̬ȥ�ģ�����û��ʲô�ڴ�������һ��ʼ�ͺ������ҡ�
�����������������������ڿ�һ����Ӱ�����������ڿ�һ����ʵ��ͥ�ļ�¼Ƭ�� ����˵�ط����ҿ�������ÿ��ĵ�Ӱ����������������ġ�û����͸��Ļ������������ļ�ֵ�ۣ�
û�������ۻ�����ȴ�޻�ζ֮������Ч�ż����е�ֻ������ʵ�ļҳ���̺�������ů��',8.0,'2015-10-12 18:23',0,default,default)
insert into TB_MOVIE_REMARK values(10004,10001,'��ͷ���ഺ','����ĺ��굵�쳣���֣���·�󵼷׷��Ƴ���Ʒ���еĴ��£��еĵ߸����еļ�����·��
���Ǻ��е�Ʊ��һ�㣬�еĲҰܣ��еĲ������ް��պ죬�й���Ӱ�г���Խ���Ӵ�ȴ����ƫ�棬�����ǵ��ݶ�ţ�棬��Ա��ǿ��Ϳ�������Ʊ��������� 
���ֵ�Ӱ�Ψ�����ط�20�꡷������ǰһ����',8.0,'2015-01-10 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10004,10002,'����ͬ�а��','���������͹۲�������Ӱ�� ����������������������ӭ���硣 ����������أ�����ͻ��ڱ�Ƭ��ֲ�롣
�ţ��������� ������ʶ���ط�20�꡷�ĵ��ݳ�������ͨ��������һ��ӰƬ�����ߴ�ʦ�������Ǻ��������ġ�һ����Ҫԭ�������ڡ����ߴ�ʦ����������ϲ����Ī��ε��
����ǡ�ð����ĳ�����ϲ�������ֵ���������һ������Ҫ�����ؾ��ǳ�����������һ�����¡�',8.0,'2015-01-09 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10004,10003,default,'��ѽ�����ط�20�꡷̫�ÿ��ˣ���Ȼ˵���ǳ���¹�ϵ���ȥ�ģ����������ı����������ˣ�
ȫƬ������㣡��ӳ��ȫ����Ц�������������ľ������ǽ����������ɶ�õĺþ籾���� [ �ط� ] ���ŶΣ�����������ģ�ⳤ��ͷ�ķ�ʽ������ƽ����̫��ͽ�����̫�棬
ǡ���ô��ĺͽ�β���ֵ� [ ����� ]�Ŷν����Ӧ�����ó�����Ϊ�˲��� [ �ط��ͱ�� ] �Ե�ͻأҲ�������˹���',7.0,'2015-01-11 18:23',3,default,default)

select * from TB_MOVIE_REMARK 




create table TB_MR_TREEPATHS	--ӰƬ��������Ϣ���ӹ�ϵ��
(
	ANCESTOR int not null foreign key references TB_CINEMA_REMARK(ID),	--�����۱��
	DESCENDANT int not null foreign key references TB_CINEMA_REMARK(ID)	--�����۱��
	primary key (ANCESTOR, DESCENDANT)
)




create table TB_VIDEO_HALL		--��ӳ����
(
	ID	int		primary key  identity(10000,1),					--��ӳ�����
	CINEMA_ID	int	not null  references TB_CINEMA(CINEMA_ID),	--��ӰԺ���
	NO			tinyint not null,								--��ӳ���ڸ�ӰԺ�ı��
	SEAT_COUNT	tinyint not null,								--��λ��
	ROW			tinyint not null,								--����
	COL			tinyint not null,								--����
	IS_EXIST	bit not null default(1),						--�Ƿ�ɾ����1��û�У�0���ǣ�
--	IS_IN_USE	bit	not null	default(0),					--(0��ʾ��δռ�ã�1��ʾ��ռ��)
	unique(CINEMA_ID,NO)
)


insert into TB_VIDEO_HALL values(10000,1,180,12,15,default)
insert into TB_VIDEO_HALL values(10000,2,200,20,10,1)
insert into TB_VIDEO_HALL values(10000,3,100,10,10,1)
insert into TB_VIDEO_HALL values(10000,4,150,15,10,1)
insert into TB_VIDEO_HALL values(10000,5,120,12,10,1)
insert into TB_VIDEO_HALL values(10000,6,180,15,12,1)

insert into TB_VIDEO_HALL values(10001,1,180,12,15,1)
insert into TB_VIDEO_HALL values(10001,2,200,20,10,1)
insert into TB_VIDEO_HALL values(10001,3,100,10,10,1)
insert into TB_VIDEO_HALL values(10001,4,150,15,10,1)
insert into TB_VIDEO_HALL values(10001,5,120,12,10,1)
insert into TB_VIDEO_HALL values(10001,6,180,15,12,1)

insert into TB_VIDEO_HALL values(10002,1,180,12,15,1)
insert into TB_VIDEO_HALL values(10002,2,200,20,10,1)
insert into TB_VIDEO_HALL values(10002,3,100,10,10,1)
insert into TB_VIDEO_HALL values(10002,4,150,15,10,1)
insert into TB_VIDEO_HALL values(10002,5,120,12,10,1)
insert into TB_VIDEO_HALL values(10002,6,180,15,12,1)

insert into TB_VIDEO_HALL values(10003,1,180,12,15,1)
insert into TB_VIDEO_HALL values(10003,2,200,20,10,1)
insert into TB_VIDEO_HALL values(10003,3,100,10,10,1)
insert into TB_VIDEO_HALL values(10003,4,150,15,10,1)
insert into TB_VIDEO_HALL values(10003,5,120,12,10,1)
insert into TB_VIDEO_HALL values(10003,6,180,15,12,1)

insert into TB_VIDEO_HALL values(10004,1,180,12,15,1)
insert into TB_VIDEO_HALL values(10004,2,200,20,10,1)
insert into TB_VIDEO_HALL values(10004,3,100,10,10,1)
insert into TB_VIDEO_HALL values(10004,4,150,15,10,1)
insert into TB_VIDEO_HALL values(10004,5,120,12,10,1)
insert into TB_VIDEO_HALL values(10004,6,180,15,12,1)

select * from TB_VIDEO_HALL




create table TB_SEAT		--��ӳ����λ��
(	
	ID	int		primary key  identity(10000,1),						--��ӳ����λ���
	VIDEO_HALL_ID	int	not null	references TB_VIDEO_HALL(ID),	--��ӳ�����
	ROW	tinyint		not null,										--��λ�ţ��У���
	COL		tinyint		not null,									--��λ�ţ��У���
	--IS_IN_USE		bit		not null	default(0)					--(1��ʾռ�ã�0��ʾδռ��)
)
select * from TB_SEAT



create table TB_SCREENINGS		--��ӳ���α�
(
	ID	bigint	primary key  identity(10000,1),							--��ӳ���α��
	MOVIE_ID	int		not null	references TB_MOVIE(MOVIE_ID),		--��Ӱ���
	VIDEO_HALL_ID	int		not null references TB_VIDEO_HALL(ID),		--��ӳ�����
	CINEMA_ID   int		not null foreign key references TB_CINEMA(CINEMA_ID),--ӰԺ���
	START_TIME	datetime	not null,									--����ʱ��
	ORIGINAL_PRICE	tinyint	not null,									--ԭ��		
	PRICE	tinyint		not null,										--��ǰ�۸�										
	IS_EXIST bit not null default(1)									--�����Ƿ���ڣ�1����/0������
)

insert into TB_SCREENINGS values(10000,10000,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10001,10001,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10002,10002,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10003,10003,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10004,10004,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10005,10005,10000,'2014-12-24 10:00',48,38,1)

select * from TB_SCREENINGS




create table TB_TICKET		--��ӰƱ��
(
	ID	bigint	primary key  identity(10000,1),					--��ӰƱ���
	SCREENINGS_ID  bigint not null references TB_SCREENINGS(ID),--���α��
	[USER_ID] int not null	references TB_USERINFO([USER_ID]),	--�û����
	ROW	tinyint	not null,										--��λ������
	COL		tinyint not null									--��λ���ںţ��У�
)

insert into  TB_TICKET values(10000,10000,8,12)

insert into  TB_TICKET values(10002,10000,8,12)

select * from TB_TICKET




create table TB_ORDER		--������
(
	ID bigint primary key  identity(10000,1),					--�������
	[USER_ID] int not null references TB_USERINFO([USER_ID]),	--�û����
	CINEMA_ID int not null	references TB_CINEMA(CINEMA_ID),	--ӰԺ���
	SCREENINGS bigint not null references TB_SCREENINGS(ID),	--���α�ţ�����ӰƬ����Ӱ������Ʊ�ۣ�
	TIME datetime not null default(getdate()),					--�µ�ʱ��
	status varchar(10) not null default('δ����')				--����״̬(��ɾ����δ����Ѹ���)
)

insert into TB_ORDER values(10000,10000,10000,default,0 )

insert into TB_ORDER values(10000,10001,10000,default,0 )

select * from TB_ORDER



create table TB_ORDER_TICKET		--������λ��ϵ��
(
	ORDER_ID bigint foreign key references TB_ORDER(ID),		--�������
	TICKET_ID bigint foreign key references TB_TICKET(ID),		--Ʊ���
	primary key	(ORDER_ID, TICKET_ID)
)