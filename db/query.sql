-- lấy tất cả các playlist songs được tạo theo role manager và staff
select playlists.* from usertype
						join accounts on usertype.accountid = accounts.email
						join author on accounts.email = author.email
						join playlists on playlists.usertypeid = usertype.usertypeid
						join playlist_recording on  playlist_recording.playlistsid = playlists.playlistid
					where author.idrole = 4 or author.idrole = 5
					group by playlists.playlistid, 
						playlists.createdate,
						playlists.description,
						playlists.ispublic,
						playlists.playlistname,
						playlists.quantity,
						playlists.image,
						playlists.usertypeid

-- lấy tất cả các playlist podcast được tạo theo role manager và staff
select playlists.* from usertype
						join accounts on usertype.accountid = accounts.email
						join author on accounts.email = author.email
						join playlists on playlists.usertypeid = usertype.usertypeid
						join playlist_podcast on  playlist_podcast.playlistid = playlists.playlistid
					where author.idrole = 4 or author.idrole = 5
					group by playlists.playlistid, 
						playlists.createdate,
						playlists.description,
						playlists.ispublic,
						playlists.playlistname,
						playlists.quantity,
						playlists.image,
						playlists.usertypeid


select top 10  episodes.* from podcast 
						left join tags on podcast.category = tags.tagid
						join  episodes on podcast.podcastid = episodes.podcastid
					where tags.tagid in (8,16) and episodes.ispublic = 1 
					ORDER BY NEWID()
					
select * from tags where tagid in (2,16)