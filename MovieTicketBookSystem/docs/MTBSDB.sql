create database MTBSDB

go


create table TB_USERINFO	--用户表
(
	USER_ID		int		primary key		identity(10000,1),		--用户编号
	ACCOUNT		varchar(30)		not null	unique,				--账号
	PASSWORD	char(32)		not null,				--密码
	HEAD_PATH	varchar(50)		not null default('images/head/default_head.jpg'),						--头像路径
	IS_ADMIN	bit	not null	default(0),				--(0表示：普通用户，1表示：管理员)
	IS_EXIST	bit not null	default(1)				--(用户是否还存在，1：是，0：不是)
)

insert into TB_USERINFO values('aa','aa',default,0,default)
insert into TB_USERINFO values('bb','bb','images/head/h001.jpg',0,default)
insert into TB_USERINFO values('cc','cc','images/head/h002.jpg',0,default)
insert into TB_USERINFO values('dd','dd',default,1,default)

select * from TB_USERINFO


create table TB_AREA		--地区表
(
	AREA_ID int not null primary key,			--区域编号
	AREA_NAME varchar(30) not null,				--行政区划名称
	PARENT_ID int not null,						--父级编号
	AREA_LEVEL tinyint not null,				--区域级别(1省/2市/3区县)
	[STATUS] bit not null default(1),			--状态(1可用/0不可用)
	FIRST_LETTER char(1) not null default(' '),	--拼音首字母(对于直辖市/2市有作用)
	IS_HOT bit not null default(0)				--行政区是否热门(1 热门/2 非热门)
)

select * from TB_AREA



create table TB_CINEMA		--电影院表
(
	CINEMA_ID	int		primary key		identity(10000,1),						--影院编号
	CINEMA_NAME	varchar(50)		not null,										--影院名称
	--DISTRICT_NAME	varchar(20)	not null,										--行政区名称
	AREA_ID int foreign key references TB_AREA(AREA_ID),						--行政区编号
	ADDRESS		varchar(100)	not null,										--影院地址
	LOGO	varchar(50)	not null default('images/cinema/default_logo.png'),		--影院logo
	SCENE	varchar(50)		 not null,											--影院实景
	TEL		varchar(15)		 not null,											--影院电话
	SPECIAL		varchar(255),													--影院特色
	DESCRIPTION		varchar(1000),												--影院描述
	VISIT_COUNT		bigint 	not null	default(0),								--影院关注
	IS_OPEN			bit		not null	default(1)								--(1表示：正在营业，0表示：未营业)	
)

insert into TB_CINEMA values('重庆UME国际影城-江北店',500105,'江北区北城天街8号北城天街购物广场B区5F(5楼老馆B区,4楼新馆A区)','images/cinema/c001.jpg',
'images/cinema/UME_movie001.jpg','023-67701166','“山城首家经国家认证的五星级影城，占地面积7000多平米，共有十五个放映厅，是目前国内不可多见的多厅现代化影城。”',
'UME重庆国际影城是香港UME影院集团继北京、上海设立影城后，于2004年12月投资兴建的第三座影城，也是山城首家经国家认证的五星级影城，占地面积7000多平米，共有十五个放映厅，
是目前国内不可多见的多厅现代化影城。 每天排映十余部新片，放映一百多场次获给市民最多选择的“电影超市”之美誉。其豪华贵宾厅、情侣影厅为国内首创。 重庆UME国际影城（江北）
平面布局图，犹如一列豪华列车，十五个厅排列有序，从售票大厅购票后，顺着长达120米的长廊依次进入1—10放映厅，坐电梯下四楼通过天桥通道即可进入新馆11——15放映厅。 
除了道具橱窗、电影玩具柜、主题咖啡厅，新馆还设有情侣套座影厅和电影书吧等全国首创的全新设施。',210979,1)
insert into TB_CINEMA values('重庆金逸国际影城-IMAX店',500108,'南岸区江南大道28号步行街协信星光时代广场L2-L3层','images/cinema/c002.jpg','images/cinema/scene_jinyi.jpg','023-62627755',
'“西南第一大巨幕，填补了西南地区高档电影城终极体验的西南第一幕空白。”','重庆金逸国际电影城星光时代店作为广州金逸影视传媒股份有限公司耗资三千多万在中国打造的首家IMAX影城，亦是西南第一大巨幕，
填补了西南地区高档电影城终极体验的西南第一幕空白。 重庆金逸国际电影城星光时代店，重庆首家IMAX五星影城，致力于科技打造视听盛宴。总建筑面积达6000 平方米，影城拥有8个豪华影厅，座位数1700余座，
同时拥有西南地区第一巨幕的IMAX3D影厅及4个双机数字厅！',139748,1 )
insert into TB_CINEMA values('重庆万达影城-大融城店',500105,'江北区建新北路8号大融城商业中心6-7楼','images/cinema/c005.jpg','images/cinema/scene_wanda.jpg','023-62906088',
'“营造年轻、时尚、简约风格，将成为青年人电影文化消费、交际的首选场所。”','重庆万达国际影城大融城店位于江北观音桥大融城6-7楼，是万达院线在重庆投资兴建的第二家多厅影城。影城共计13个影厅，
总计约1600个座席；大融城店按照主流五星级影院标准进行建设，且采用设计感极强的后现代设计风格，营造年轻、时尚、简约风格，将成为青年人电影文化消费、交际的首选场所；
重庆万达国际影城大融城店全部采用数字放映机，所有放映、音响设备均采用国际顶级品牌：放映设备全部实现数字化、并建立数字影院管理系统（TMS），数字放映机采用世界著名品牌：
科视公司、巴可公司影院数字放映机系列产品；数字播放服务器采用杜比公司、GDC公司产品；还音核心设备（解码器）采用世界著名品牌杜比公司CP650\CP750系列产品和DTS公司产品；
影院音响系统采用世界著名品牌JBL音箱、皇冠功放、QSC音箱和功放；3D放映系统。',155783,1 )
insert into TB_CINEMA values('重庆华谊兄弟影城-袁家岗店',500107,'九龙坡区袁家岗奥体路1号中新城上城3楼','images/cinema/c003.jpg','images/cinema/scene_huayi.jpg','023-62626670',
'“华谊兄弟影院袁家岗店引入国内最先进的双机3D技术，给观众带来水晶般亮丽清晰的3D画质。在放映方面，采用国际先进的影院自动放映管理系统，”','华谊兄弟影院袁家岗店经营面积近6000平方米。
拥有8个影厅，其中包含1个双机3D影厅，2个导演厅，1个情侣厅，1个贵宾休息区，共计915个座位。从放映及音响器材到银幕、座椅，全部采用国外最新型设备，致力于为观影者打造高品质的观影环境。
高起坡、低视角、弧形宽银幕可让观众全方位感受电影魅力，享受高水准的视听空间，画面效果惊人，彰显大气风范；另还拥有媲美头等舱的2个尊贵导演厅，1个私密浪漫的情侣厅，
又融合了各种时尚潮流、浪漫的元素，彰显出小资情调的浪漫风采。一座电影院、8个风格迥异的放映厅，造就了影院观影环境的别具一格。此外，华谊兄弟影院袁家岗店引入国内最先进的双机3D技术，
给观众带来水晶般亮丽清晰的3D画质。在放映方面，采用国际先进的影院自动放映管理系统，数字化自动控制。',71573,1 )
insert into TB_CINEMA values('横店电影城（重庆煌华店）',500106,'沙坪坝区三峡广场步行街6号煌华新纪元购物广场8楼','images/cinema/c006.jpg','images/cinema/scene_hengdian.jpg','023-65007645',
'“影城按照国际五星级影院最高标准设计、建造，装修豪华，设施高档，环境一流，观影舒适，是重庆地区档次最高的影城。”','重庆煌华横店电影城是一家五星级豪华多功能影城，影城按照国际五星级影院最
高标准设计、建造，装修豪华，设施高档，环境一流，观影舒适，是重庆地区档次最高的影城。影城面积近4000平方米，建有9个一流的国际标准专业影厅和1个豪华VIP厅，可容纳1000余名观众。10个国际标准影
厅配置了当今世界上最为高档的视听设备，美国JBL功放和音响，英国DOLBY数字解码器，德国进口高清晰放映镜头，英国哈尼克斯高反射率银幕。一流的视听设备，一流的视听效果，给您带来从未有过的视听震撼。
丰富的厅、堂场所，优质的服务软、硬件配置，能为社会各企事业单位提供观影、会议、讲课、晚会等活动服务，同时也能为各品牌、产品提供宣传、展示的平台。',78544,1 )

select * from TB_CINEMA


create table TB_SELLER		--商家表
(
	SELLER_ID	int		primary key		identity(10000,1),			--商家编号
	ACCOUNT		varchar(30)		not null	unique,					--商家账号
	PASSWORD	char(32)		not null,							--商家密码
--	CINEMA_ID	int			unique	references TB_CINEMA(CINEMA_ID),	--电影院编号
	IS_EXIST	bit		not null	default(1),			--商家是否存在（1：是，0:不是）	
)
insert into TB_SELLER values('aaa','aaaaaa',default)
insert into TB_SELLER values('bbb','bbbbbb',default)
insert into TB_SELLER values('ccc','cccccc',default)
insert into TB_SELLER values('ddd','dddddd',default)
insert into TB_SELLER values('eee','eeeeee',default)

select * from TB_SELLER


create table TB_SELLER_CINEMA		--商家-影院关联信息表
(
	SELLER_ID int foreign key references TB_SELLER(SELLER_ID),		--商家编号
	CINEMA_ID int foreign key references TB_CINEMA(CINEMA_ID)		--影院编号
)
insert into TB_SELLER_CINEMA values(10000, 10000)
insert into TB_SELLER_CINEMA values(10001, 10001)
insert into TB_SELLER_CINEMA values(10002, 10002)
insert into TB_SELLER_CINEMA values(10003, 10003)
insert into TB_SELLER_CINEMA values(10004, 10004)

select * from TB_SELLER_CINEMA



create table TB_ACTOR		--演员/导演表
(
	ACTOR_ID		int		primary key		identity(10000,1),		--演员/导演编号
	ACTOR_NAME	varchar(50)		not null							--演员名称
)

insert into TB_ACTOR values('张涵予')
insert into TB_ACTOR values('梁家辉')
insert into TB_ACTOR values('林更新')
insert into TB_ACTOR values('佟丽娅')
insert into TB_ACTOR values('陈晓')
insert into TB_ACTOR values('韩庚')
insert into TB_ACTOR values('陈赫')
insert into TB_ACTOR values('杨颖')
insert into TB_ACTOR values('张鲁一')
insert into TB_ACTOR values('曹璐')
insert into TB_ACTOR values('王姬')
insert into TB_ACTOR values('蒋雯丽')
insert into TB_ACTOR values('姜瑞佳')
insert into TB_ACTOR values('佟大为')
insert into TB_ACTOR values('王宝强')
insert into TB_ACTOR values('文章')
insert into TB_ACTOR values('林志玲')
insert into TB_ACTOR values('钟欣潼')
insert into TB_ACTOR values('刘瑾')
insert into TB_ACTOR values('蒙亭宜')
insert into TB_ACTOR values('邬君梅')
insert into TB_ACTOR values('郑则仕')
insert into TB_ACTOR values('刘璐佳')
insert into TB_ACTOR values('李铭顺')
insert into TB_ACTOR values('葛优')
insert into TB_ACTOR values('周韵')
insert into TB_ACTOR values('刘利年')
insert into TB_ACTOR values('杨子姗')
insert into TB_ACTOR values('陈柏霖')
insert into TB_ACTOR values('鹿晗')
insert into TB_ACTOR values('归亚蕾')
insert into TB_ACTOR values('舒淇')
insert into TB_ACTOR values('那英')
insert into TB_ACTOR values('王志文')
insert into TB_ACTOR values('洪晃')
insert into TB_ACTOR values('王德顺')

select * from TB_ACTOR




create	table TB_MOVIE		--影片表
(
	MOVIE_ID	int		primary key		identity(10000,1),			--影片编号
	NAME	varchar(50)		not null,								--影片名称
	POST	varchar(50)		not null,								--影片海报
	--DIRECTOR_ID		int		references TB_ACTOR(ACTOR_ID),			--导演编号  
	TITLE	varchar(50),											--影片标题
	IS_3D	bit	not null	default(0),							--是否为3D(0表示：不是，1表示：是)
	AREA	varchar(50)	not null		default('中国'),		    --影片地区
	DURATION	tinyint	not null,									--时长（单位：分钟）
	[LANGUAGE]	varchar(50)	not null,								--语言
	PUBLISH		datetime	not null,								--上映时间
	[DESCRIPTION]	varchar(1000)	not null,							--剧情简介
	LIKE_COUNT	int		not null	default(0),						--喜欢数目
	ATTENTION_COUNT	int		not null	default(0),					--关注数目
	BUY_COUNT	int		not null	default(0),						--购票数目		
	IS_EXIST	bit not null	default(1)							--(影片是否还存在，1：是，0：不是)			
)

insert into TB_MOVIE values('智取威虎山','images/movie/mbig001.jpg','"化老气题材为狂霸酷炫屌炸天“',1,default,141,'国语','2014-12-23',
'   1947年冬，东北民主联军203小分队在首长少剑波（林更新 饰）的带领下，奉上级命令进入匪患猖獗的林海雪原保护百姓安全，侦查员杨子荣（张涵予 饰）
与卫生员白茹（佟丽娅 饰）火线驰援。为彻底瓦解土匪势力，杨子荣执意请求乔装潜入匪窝“威虎山”。历经了重重考验的杨子荣因献宝有功，被匪首座山雕封为“威虎山老九”。
	杨子荣一面与八大金刚周旋，一面涉险为山下战友传出情报，而山寨中的一名神秘女子却屡屡将他陷于生死绝境。被203小分队生擒的土匪联络副官栾平趁乱逃脱，
竟出现在威虎寨中与杨子荣当面对质……座山雕（梁家辉 饰 ）寿辰“百鸡宴”上，杨子荣与203小分队的战友们迎来了剿匪收网的最佳时机，一场鏖战在所难免……',
275759, 1324944, 918322, default)
insert into TB_MOVIE values('微爱之渐入佳境','images/movie/mbig002.jpg','”勾搭是一种美德，狗搭是一种时尚。“',0,default,98,'国语','2014-12-24',
'    一个都市微男沙果（陈赫 饰）想用微信寻找一份真诚的爱情，岂料却被时尚大威女陈西（杨颖 饰）拐到庸俗的一夜情上，但沙果始终坚信:勾搭是一种美德，
狗搭是一种时尚。历经千回百折, 最后，这对微男威女竟然在五台山脚下狭路相逢……',
88293,403909,379372, default)
insert into TB_MOVIE values('一步之遥','images/movie/mbig003.jpg','”要么爱死，要么作死“',1,default,140,'国语','2014-12-18',
'   北洋年间，东方魔都，花国大选如火如荼，各国佳丽争相斗艳，万众瞩目，全城狂欢,两届总统完颜英（舒淇 饰）惊险连任，大选操办人马走日（姜文 饰）因此名满天下。
然而一场谋杀颠覆了一切，完颜英命丧黄泉，马走日亡命天涯，并和操办大选的另一搭档、发小项飞田（葛优 饰）由莫逆之交变为生死宿敌，而将府名媛武六（周韵 饰）的出现，
改变了所有人的命运，魔都双雄展开生死对决，惊天冒险一触即发，生死爱恋一步之遥……',
217134,1133990,690618, default)
insert into TB_MOVIE values('王牌','images/movie/mbig004.jpg',null,0,default,90,'国语','2014-12-31',
'   20世纪30年代，风波诡谲的上海，正是白色 恐怖弥漫时期，中共地下党小心而敏锐地存活着。一个绝密会议因为判徒的出卖而被破坏，会议现场周边的人悉数被捕。
地下党负责人王峰（梁家辉 饰）突围后要求不惜一切代价迅速组织营救，原来1号人物“王牌”恰在被捕者其中。敌人闻讯大喜却苦于无法甄别真正的“王牌”，于是动用各种残忍极刑，
企图 从嫌疑最大的五名女性——“小学教师”赵碧薇（林志玲 饰）、“交际花”苏捷（阿娇 饰）、“茶楼老板娘”王霞芬（蒙亭宜 饰）、“大学教授”施老师（苏瑾 饰）以及偶然被牵进事件
中的“女学生”小红等人口中撬出“王牌”的真相……最终狱中推出了“王牌”的尸体，将所有人推到绝望边缘。
    这是一次失败的营救，是谁泄露了“王牌”的真实身份？直到解放后赵碧薇儿子18岁这一天，她终于鼓足勇气将当年证词全部推翻，也因此将历劫存活下来的人们重新拖回了炼狱般的酷刑回忆…… ',
8027, 57359,22426, default)
insert into TB_MOVIE values('重返20岁','images/movie/mbig005.jpg','"谁不想做个返老还童的美梦？"',0,default,131,'国语','2015-01-08',
'   70岁的老奶奶沈梦君（归亚蕾 饰）不可思议变身为20岁妙龄少女孟丽君（杨子姗 饰）后，以新身份回到日常生活，并帮助自己的孙子创办乐队，
而引发的一系列啼笑皆非的故事：她重拾音乐梦想，并再次邂逅爱情，和不同年龄的几位男士：高冷音乐总监谭子明（陈柏霖 饰）、乐队吉他手歌手项前进（鹿晗 饰）、
迷恋沈梦君的老爷爷李大海（王德顺 饰）神奇触电。',
49085,370470,212536, default)

select * from TB_MOVIE 



create table TB_DIRECTOR			--导演表
(
	DIRECTOR_ID int not null primary key identity(10000, 1),	--导演编号
	DIRECTOR_NAME varchar(50) not null							--导演名字
)
insert into TB_DIRECTOR values('徐克')
insert into TB_DIRECTOR values('顾长卫')
insert into TB_DIRECTOR values('姜文')
insert into TB_DIRECTOR values('范建会')
insert into TB_DIRECTOR values('陈正道')

select * from TB_DIRECTOR



create table TB_MOVIE_DIRECTOR				--影片-导演信息关联表
(
	MOVIE_ID int foreign key references TB_MOVIE(MOVIE_ID),			--影片编号
	DIRECTOR_ID int foreign key references TB_DIRECTOR(DIRECTOR_ID)	--导演编号
	unique(MOVIE_ID, DIRECTOR_ID)
)

insert into TB_MOVIE_DIRECTOR values(10000, 10000)
insert into TB_MOVIE_DIRECTOR values(10001, 10001)
insert into TB_MOVIE_DIRECTOR values(10002, 10002)
insert into TB_MOVIE_DIRECTOR values(10003, 10003)
insert into TB_MOVIE_DIRECTOR values(10004, 10004)

select * from TB_MOVIE_DIRECTOR


 
create table TB_MOVIE_ACTOR	--影片-演员表
(
	MOVIE_ID		int				references TB_MOVIE(MOVIE_ID),		--影片编号
	ACTOR_ID		int				references TB_ACTOR(ACTOR_ID),		--演员编号
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




create table TB_MOVIE_TYPE	--影片-类型表
(
	MOVIE_ID		int			references TB_MOVIE(MOVIE_ID),						--影片编号
	TYPE_NAME	varchar(10)	not null,												--类型编号
	primary key (MOVIE_ID,TYPE_NAME)
)
insert into TB_MOVIE_TYPE values(10000,'战争')
insert into TB_MOVIE_TYPE values(10000,'动作')

insert into TB_MOVIE_TYPE values(10001,'爱情')

insert into TB_MOVIE_TYPE values(10002,'剧情')
insert into TB_MOVIE_TYPE values(10002,'喜剧')


insert into TB_MOVIE_TYPE values(10003,'悬疑')
insert into TB_MOVIE_TYPE values(10003,'剧情')
insert into TB_MOVIE_TYPE values(10003,'战争')

insert into TB_MOVIE_TYPE values(10004,'剧情')
insert into TB_MOVIE_TYPE values(10004,'喜剧')

select * from TB_MOVIE_TYPE



create table TB_CINEMA_REMARK		--影院评价表
(
	ID		int		primary key  identity(10000,1),			--评价编号
	CINEMA_ID	int		references TB_CINEMA(CINEMA_ID),	--电影院编号
	[USER_ID]	int		references TB_USERINFO(USER_ID),--用户编号
	CONTENT		varchar(150)	not null,				--评价内容
	GRADE		decimal(2,1)	default(0.0),				--得分
	[TIME]		datetime	not null,				--评价时间
	LIKE_COUNT	int		not null	default(0),		--被赞数
	IS_EXIST 	bit		not null	default(1),		--评论是否存在，1存在/0不存在
	STATUS		tinyint		not null	default(1)		--评论的状态，1尚未审核，2审核通过，3审核未通过
)
insert into TB_CINEMA_REMARK values(10000,10000,'对UME是绝对的爱，从影院在重庆开业到现在，最爱的影院，没有之一。 ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10000,10001,'环境好，交通方便，吃喝玩乐一条龙！影音效果震撼！支持支持！。 ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10000,10002,'绝不再去的电影院，3D还是最落后的单机3D，夜景黑乎乎的，完全仗着地理位置好。 ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10000,10003,'环境不错，周边配套齐，就是服务员服务差一点，有点自以为是的感觉。 ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10000,10003,'首先厅多选择多，排片也非常合理，装修豪华，硬件一流，关键是经常搞活动，而且DMAX巨幕厅够大够震撼，效果杠杠的 与IMAX不相上下，但价格却比IMAX实惠得多~ 。 ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10000,10001,'垃圾影院。除了位置好点。条件差，票价贵。关键是服务员态度极其恶劣，连饮料都不让自带。不是逼不得已是不会去这家的。 ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10001,10000,'对金逸是绝对的爱，从影院在重庆开业到现在，最爱的影院，没有之一。 ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10001,10001,'环境好，交通方便，吃喝玩乐一条龙！影音效果震撼！支持支持！。 ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10001,10002,'绝不再去的电影院，3D还是最落后的单机3D，夜景黑乎乎的，完全仗着地理位置好。 ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10001,10003,'环境不错，周边配套齐，就是服务员服务差一点，有点自以为是的感觉。 ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10001,10003,'首先厅多选择多，排片也非常合理，装修豪华，硬件一流，关键是经常搞活动，而且DMAX巨幕厅够大够震撼，效果杠杠的 与IMAX不相上下，但价格却比IMAX实惠得多~ 。 ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10001,10001,'垃圾影院。除了位置好点。条件差，票价贵。关键是服务员态度极其恶劣，连饮料都不让自带。不是逼不得已是不会去这家的。 ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10002,10000,'对华谊是绝对的爱，从影院在重庆开业到现在，最爱的影院，没有之一。 ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10002,10001,'环境好，交通方便，吃喝玩乐一条龙！影音效果震撼！支持支持！。 ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10002,10002,'绝不再去的电影院，3D还是最落后的单机3D，夜景黑乎乎的，完全仗着地理位置好。 ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10002,10003,'环境不错，周边配套齐，就是服务员服务差一点，有点自以为是的感觉。 ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10002,10003,'首先厅多选择多，排片也非常合理，装修豪华，硬件一流，关键是经常搞活动，而且DMAX巨幕厅够大够震撼，效果杠杠的 与IMAX不相上下，但价格却比IMAX实惠得多~ 。 ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10002,10001,'垃圾影院。除了位置好点。条件差，票价贵。关键是服务员态度极其恶劣，连饮料都不让自带。不是逼不得已是不会去这家的。 ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10003,10000,'对万达是绝对的爱，从影院在重庆开业到现在，最爱的影院，没有之一。 ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10003,10001,'环境好，交通方便，吃喝玩乐一条龙！影音效果震撼！支持支持！。 ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10003,10002,'绝不再去的电影院，3D还是最落后的单机3D，夜景黑乎乎的，完全仗着地理位置好。 ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10003,10003,'环境不错，周边配套齐，就是服务员服务差一点，有点自以为是的感觉。 ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10003,10003,'首先厅多选择多，排片也非常合理，装修豪华，硬件一流，关键是经常搞活动，而且DMAX巨幕厅够大够震撼，效果杠杠的 与IMAX不相上下，但价格却比IMAX实惠得多~ 。 ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10003,10001,'垃圾影院。除了位置好点。条件差，票价贵。关键是服务员态度极其恶劣，连饮料都不让自带。不是逼不得已是不会去这家的。 ',5.0,'2014-12-28 20:25',0,default,default)

insert into TB_CINEMA_REMARK values(10004,10000,'对横店是绝对的爱，从影院在重庆开业到现在，最爱的影院，没有之一。 ',null,'2014-12-20 10:25',5,default,default)
insert into TB_CINEMA_REMARK values(10004,10001,'环境好，交通方便，吃喝玩乐一条龙！影音效果震撼！支持支持！。 ',8.0,'2014-12-22 15:25',3,default,default)
insert into TB_CINEMA_REMARK values(10004,10002,'绝不再去的电影院，3D还是最落后的单机3D，夜景黑乎乎的，完全仗着地理位置好。 ',null,'2014-12-24 12:24',0,default,default)
insert into TB_CINEMA_REMARK values(10004,10003,'环境不错，周边配套齐，就是服务员服务差一点，有点自以为是的感觉。 ',7.0,'2014-12-23 11:05',2,default,default)
insert into TB_CINEMA_REMARK values(10004,10003,'首先厅多选择多，排片也非常合理，装修豪华，硬件一流，关键是经常搞活动，而且DMAX巨幕厅够大够震撼，效果杠杠的 与IMAX不相上下，但价格却比IMAX实惠得多~ 。 ',9.0,'2014-12-26 17:25',10,default,default)
insert into TB_CINEMA_REMARK values(10004,10001,'垃圾影院。除了位置好点。条件差，票价贵。关键是服务员态度极其恶劣，连饮料都不让自带。不是逼不得已是不会去这家的。 ',5.0,'2014-12-28 20:25',0,default,default)

select * from TB_CINEMA_REMARK



create table TB_CR_TREEPATHS	--影院的评价信息父子关系表
(
	ANCESTOR int not null foreign key references TB_CINEMA_REMARK(ID),	--父评论编号
	DESCENDANT int not null foreign key references TB_CINEMA_REMARK(ID)	--子评论编号
	primary key (ANCESTOR, DESCENDANT)
)




create table TB_MOVIE_REMARK		--影片评价表
(
	ID	int		primary key  identity(10000,1),			--评价编号
	MOVIE_ID		int		references TB_MOVIE(MOVIE_ID),	--影片编号
	[USER_ID]	int		references TB_USERINFO(USER_ID),	--用户编号
	TITLE		varchar(50)		not null default('影评'),	--影评标题
	CONTENT		varchar(3000)	not null,				--评价内容
	GRADE		decimal(2,1)	not null default(7.0),			--得分
	[TIME]	datetime	not null,					--评价时间
	LIKE_COUNT	int		not null	default(0),		--被赞数
	IS_EXIST 	bit		not null	default(1),		--评论是否存在，1存在/0不存在
	STATUS		tinyint		not null	default(1)		--评论的状态，1尚未审核，2审核通过，3审核未通过
)

insert into TB_MOVIE_REMARK values(10000,10000,default,'小时候就看过曲波小说《林海雪原》改编的电视剧，那个时候老妈还给我介绍，
看了一集就感觉很棒，感觉演的很真，特别是土匪下山对村民的烧杀抢夺，尤其土匪让村民脱了衣服在雪地里，拿着狼牙棒砸向他们，看着很心痛。
恨不得自己早出生几十年跟着抗日联军剿匪，但是看到土匪残忍凶狠一面的同时也能看到拯救劳苦**于水火的共产党领导的解放军的可爱，
希望这部影片带给大家的不只是3D效果带来精彩刺激，也让我们记得革命前辈们为了后代我们的新生活做出的流血牺牲，
更懂得现在生活来之不易，好好生活，珍惜身边人和现在。',8.5,'2014-12-24 12:20',0,default,default)
insert into TB_MOVIE_REMARK values(10000,10001,'局红管亮,公鸡中的战斗机','我想很多年轻观众不仅是误会了徐克，更多的是误会了“样板戏”。
所谓“样板戏”，的确饱蘸着极“左”年代的意识形态指向和教条化的艺术表达形式，但妖魔化“样板戏”也跟当年神化“样板戏”一样不可取。譬如《智取威虎山》
（《林海雪原》），作为一个经典的文艺作品，早就有过小说、戏剧（戏曲）、电影、电视剧、动画片等多个不同的文本样貌，此番被打造成3D版的贺岁大片，
也符合时下电影圈流行的“大IP”理念——《爸爸去哪儿》都能搁在电影院里放，况《智取威虎山》乎？',8.0,'2014-12-25 14:23',0,default,default)
insert into TB_MOVIE_REMARK values(10000,10002,'感觉很好看','感觉很好看感觉很好看感觉很好看感觉很好看',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10000,10003,'经典桥段，霸气十足！','首先一开始想看这部电影是冲着林更新去的，因为很喜欢他，他诠释过不少角色都很不错，
唯独没见过他演近代的战争片，所以这次角色的转换很想让我尝试体验；其次，徐老怪的电影不用多说，画面感是最强的，而且少不了炫酷的特效和逼真的动作，
这点在本片中体现的尤为突出；第三，故事情节方面，不仅有经典的杨子荣智取威虎山历史桥段作为背景为观众所熟知，更在枪炮打斗的战争戏中蕴含着满满的温情，
卫生员小白鸽是其中贯穿，栓子更是，也不要说韩庚打酱油，他也是感情线中不可缺少的一部分；第四，演员阵容的精良和强大更是吸人眼球般的没得说，张涵予、梁家辉、
林更新、佟丽娅、余男都是很棒的演员；最后，就是本片很有特色的一点，大量的东北土匪方言，听的我们是各种新鲜啊，如果没有字幕括号内的解释，当真是不懂了呵呵。
不过综上所述，总体感觉还是很好的，所以口碑爆棚也就不足为奇了！',8.0,'2014-12-26 18:23',5,default,default)

insert into TB_MOVIE_REMARK values(10001,10000,'感觉很好看','感觉很好看感觉很好看感觉很好看感觉很好看',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10001,10001,'一边赚钱，一边嫌钱脏','第五代的导演们如今过得都有些尴尬，这批深深打上时代烙印的创作者，迟迟无法从自己的时代里走出来，结果纷纷变成了老同志。什么是老同志？其实无关年龄，文艺青年过了气，又放不平心态，放不低架子，偏要以为自己的时代才是正路。',7.0,'2014-12-26 18:23',2)
insert into TB_MOVIE_REMARK values(10001,10002,default,'看完“微爱之渐入佳境”，有种莫名的感动，第一次有写影评的冲动：沙果作为一个小编剧，
投资方在他面前画了一个又一个大饼，为了满足投资方的各种要求，当然也是为了自己的梦想，一刻不敢停地修改自己的剧本，从广告植入，改到小清晰，
又改到惊悚，以致后来的大数据...投资方的价码也逐步攀升，沙果从没有时间吃饭睡觉，到没有时间谈朋友，最后几近癫狂，为了自己的梦想在身心的悬崖边上坚持着，
坚持着...突然，投资方为了更大的利益转手把饼给了别人，所有的梦想和努力在一夜间化为泡影，最后落得一无所有，没有人在乎这个小编剧的死活，
个中滋味也只有沙果自己明了。梦想是要有，但是开始不能吹得太大，泡泡越大，撑爆的可能性越大，渐入佳境才是最好的。——献给所有有梦想并正在为之坚持的人。',7.0,'2014-12-28 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10001,10000,'《微爱》：渐渐地爱OR贱贱地爱','这部电影简而言之就是屌丝追上女神的故事。屌丝文艺青年看似很贱，
却有着一颗执着追求艺术道路的高贵之心，他对艺术的爱与对女神的爱一样，执着、坚定、真诚、可爱。这种爱既是日积月累渐渐发生的，又是死皮赖脸的贱贱的爱
。但无论怎样，他的灵魂是值得尊敬的，从未出卖自己。陈赫的表演可圈可点，之前他在《匆匆那年》中我觉得就是一个假正经的傻缺公务员，没想到他的喜剧天赋是融入股子里的，
这次的表现更为活灵活现、淋漓尽致。我想，这种酷似路人的大叔长相能具备如此可塑性，也只有天赋可以解释了。',7.5,'2014-12-28 19:23',0,default,default)

insert into TB_MOVIE_REMARK values(10002,10000,'感觉很好看','感觉很好看感觉很好看感觉很好看感觉很好看',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10002,10001,'假以时日，一步之遥','对于刷完两遍的『一步之遥』越琢磨一直心里玩味很久。一遍，位置跑偏，
旁边还有仨小孩目测不超过五年级，注定了戏里戏外只觉一场闹剧。可不甘心，又特想邀表姐感受一下UA正排的imax，这里捎带手了我一桩心底话，
在我心目中她一直是一个情商极高的女子，事实如此。遂然在观影前已做好一打哈欠就撤的心理预防针。一切对了，有趣的来了，真心觉得好看，看得很兴奋。
对之前那些因为自己看不懂或不喜欢还好没有就信口开河骂烂片。姜文没有辜负姜文这两个字。马走日就是不愿意对完颜英说我爱你，姜文也不肯随便对观众说这三个字。',6.0,'2014-12-22 18:23',0,default,default)
insert into TB_MOVIE_REMARK values(10002,10000,'感觉很好看','感觉很好看感觉很好看感觉很好看感觉很好看',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10002,10000,'感觉很好看','看完电影到现在已经两周，看了姜文自己为一步之遥作的解释，慢慢对电影想表达的东西有所了解了。
表现手法和姜文的冷幽默不多做评价，至少视觉上华丽，有些段子也能一笑。只是突然被影片反映的东西给亮到了，那种“人们只想看到自己想看到的东西，并不想知道事实是怎样”的感觉，
大概是他想表达的东西吧。',8.0,'2014-12-29 18:23',0,default,default)


insert into TB_MOVIE_REMARK values(10003,10000,'感觉很好看','感觉很好看感觉很好看感觉很好看感觉很好看',8.0,'2014-12-26 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10003,10001,default,'深夜看完，不值票价。家里电脑上看看就够了。李铭顺作为第一反派就是个打酱油的。倒是**卧底层出不穷。
剧情有点绕，和风声完全不搭界，和罗生门也不是一码事。5个抓进去的，学妹是顶替老妈**卧底去茶楼通风报信的，她母亲为了救女儿把校工当作王牌出卖了。
志玲是**卧底的情人，还怀了孕，情人让她冒充王牌她胆怯了没认。',7.0,'2015-01-02 18:23',0,default,default)
insert into TB_MOVIE_REMARK values(10003,10002,'感觉还不错','牢房里中技老师和舞场女两个叛徒，或被博士策反，
证词为博士所言第一和第二个证据，第三证据为小红母亲所供；博士在审问王和志玲扮演角色时开始应该说了两个叛徒所述，
所以志玲扮演角色在北京见到两人时惊诧害怕，两叛徒应该是在解放后为敌方潜伏特务，所以王峰所说王牌任务还在继续，
且告诉志玲扮演角色她是从北京被救回来的；两叛徒应该不知道王牌是谁，但知道牺牲的王是共产党员',7.0,'2015-01-03 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10003,10003,'感觉很好看','感觉很好看感觉很好看感觉很好看感觉很好看',8.0,'2015-01-04 18:23',2,default,default)

insert into TB_MOVIE_REMARK values(10004,10000,'《重返》很舒服','绝对的惊喜之作，抱着看着玩的心态去的，本来没有什么期待。但是一开始就很吸引我。
很生活化，生活化到让你忘记你在看一场电影，而觉得是在看一个真实家庭的纪录片。 不能说重返是我看过的最好看的电影，但绝对是最舒服的。没有人透过幕布像你灌输他的价值观，
没有令人眼花缭乱却无回味之处的特效炫技，有的只是最真实的家长里短和人情冷暖。',8.0,'2015-10-12 18:23',0,default,default)
insert into TB_MOVIE_REMARK values(10004,10001,'回头的青春','今年的贺岁档异常热闹，各路大导纷纷推出作品，有的创新，有的颠覆，有的坚守老路。
于是乎有的票房一般，有的惨败，有的不错。花无百日红，中国电影市场虽越发庞大却剑走偏锋，不再是导演多牛叉，演员多强大就可以锁定票房的年代。 
种种电影里，唯独《重返20岁》让人眼前一亮。',8.0,'2015-01-10 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10004,10002,'都是同行帮衬','不中立不客观不第三方影评 　　声明：爱看不看，欢迎对喷。 　　利益相关：服务客户在本片有植入。
嗯，就这样。 　　认识《重返20岁》的导演陈正道是通过他的上一部影片《催眠大师》，还是很欣赏他的。一个主要原因是他在《催眠大师》里用了我喜欢的莫文蔚，
而且恰好把她拍成了我喜欢的那种调调。另外一个更重要的因素就是陈正道讲好了一个故事。',8.0,'2015-01-09 18:23',2,default,default)
insert into TB_MOVIE_REMARK values(10004,10003,default,'哎呀！《重返20岁》太好看了！虽然说我是冲着鹿晗的颜去的！但是我真心被剧情征服了！
全片毫无尿点！放映中全场大笑不计其数！真心觉得这是近几年来不可多得的好剧本！在 [ 重返 ] 的桥段，导演运用了模拟长镜头的方式体现了平行蒙太奇和交叉蒙太奇，
恰到好处的和结尾部分的 [ 变回来 ]桥段交相呼应！看得出导演为了不让 [ 重返和变回 ] 显得突兀也是下足了功夫。',7.0,'2015-01-11 18:23',3,default,default)

select * from TB_MOVIE_REMARK 




create table TB_MR_TREEPATHS	--影片的评价信息父子关系表
(
	ANCESTOR int not null foreign key references TB_CINEMA_REMARK(ID),	--父评论编号
	DESCENDANT int not null foreign key references TB_CINEMA_REMARK(ID)	--子评论编号
	primary key (ANCESTOR, DESCENDANT)
)




create table TB_VIDEO_HALL		--放映厅表
(
	ID	int		primary key  identity(10000,1),					--放映厅编号
	CINEMA_ID	int	not null  references TB_CINEMA(CINEMA_ID),	--电影院编号
	NO			tinyint not null,								--放映厅在该影院的编号
	SEAT_COUNT	tinyint not null,								--座位数
	ROW			tinyint not null,								--排数
	COL			tinyint not null,								--列数
	IS_EXIST	bit not null default(1),						--是否被删除（1：没有，0：是）
--	IS_IN_USE	bit	not null	default(0),					--(0表示：未占用，1表示：占用)
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




create table TB_SEAT		--放映厅座位表
(	
	ID	int		primary key  identity(10000,1),						--放映厅座位编号
	VIDEO_HALL_ID	int	not null	references TB_VIDEO_HALL(ID),	--放映厅编号
	ROW	tinyint		not null,										--座位排（行）数
	COL		tinyint		not null,									--座位号（列）数
	--IS_IN_USE		bit		not null	default(0)					--(1表示占用，0表示未占用)
)
select * from TB_SEAT



create table TB_SCREENINGS		--放映场次表
(
	ID	bigint	primary key  identity(10000,1),							--放映场次编号
	MOVIE_ID	int		not null	references TB_MOVIE(MOVIE_ID),		--电影编号
	VIDEO_HALL_ID	int		not null references TB_VIDEO_HALL(ID),		--放映厅编号
	CINEMA_ID   int		not null foreign key references TB_CINEMA(CINEMA_ID),--影院编号
	START_TIME	datetime	not null,									--开场时间
	ORIGINAL_PRICE	tinyint	not null,									--原价		
	PRICE	tinyint		not null,										--当前价格										
	IS_EXIST bit not null default(1)									--场次是否存在，1存在/0不存在
)

insert into TB_SCREENINGS values(10000,10000,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10001,10001,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10002,10002,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10003,10003,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10004,10004,10000,'2014-12-24 10:00',48,38,1)
insert into TB_SCREENINGS values(10005,10005,10000,'2014-12-24 10:00',48,38,1)

select * from TB_SCREENINGS




create table TB_TICKET		--电影票表
(
	ID	bigint	primary key  identity(10000,1),					--电影票编号
	SCREENINGS_ID  bigint not null references TB_SCREENINGS(ID),--场次编号
	[USER_ID] int not null	references TB_USERINFO([USER_ID]),	--用户编号
	ROW	tinyint	not null,										--座位所在排
	COL		tinyint not null									--座位所在号（列）
)

insert into  TB_TICKET values(10000,10000,8,12)

insert into  TB_TICKET values(10002,10000,8,12)

select * from TB_TICKET




create table TB_ORDER		--订单表
(
	ID bigint primary key  identity(10000,1),					--订单编号
	[USER_ID] int not null references TB_USERINFO([USER_ID]),	--用户编号
	CINEMA_ID int not null	references TB_CINEMA(CINEMA_ID),	--影院编号
	SCREENINGS bigint not null references TB_SCREENINGS(ID),	--场次编号（包含影片名，影厅名，票价）
	TIME datetime not null default(getdate()),					--下单时间
	status varchar(10) not null default('未付款')				--订单状态(已删除，未付款，已付款)
)

insert into TB_ORDER values(10000,10000,10000,default,0 )

insert into TB_ORDER values(10000,10001,10000,default,0 )

select * from TB_ORDER



create table TB_ORDER_TICKET		--订单座位关系表
(
	ORDER_ID bigint foreign key references TB_ORDER(ID),		--订单编号
	TICKET_ID bigint foreign key references TB_TICKET(ID),		--票编号
	primary key	(ORDER_ID, TICKET_ID)
)