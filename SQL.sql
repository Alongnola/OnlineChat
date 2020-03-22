DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(15) character set latin1 NOT NULL default '',
  `password` varchar(15) character set latin1 NOT NULL default '',
  `created_date` datetime default NULL,
  `headUrl` varchar(64) character set latin1 NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL auto_increment,
  `content` text character set latin1 NOT NULL,
  `created_date` datetime default NULL,
  `headUrl` varchar(64) character set latin1 NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;