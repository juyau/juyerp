		function addTab(title, href){   
			var tt = $('#tabs');   
			if (tt.tabs('exists', title)){          
				tt.tabs('select', title);   
				refreshTab({tabTitle:title,url:href});
			} else {

                var content='error';
				if (href){
					 content = '<iframe class="iframe" scrolling="yes" frameborder="0" src="'+href+'" style="width:100%;height:100%;"></iframe>';
				}

				tt.tabs('add',{
					title:title,   
					closable:true,   
					content:content,   
					iconCls:'icon-default',
					padding:'0px'
				});
			}   
		}   
		function refreshTab(cfg){
			var refresh_tab = cfg.tabTitle?$('#tabs').tabs('getTab',cfg.tabTitle):$('#tabs').tabs('getSelected');   
			if(refresh_tab && refresh_tab.find('iframe').length > 0){   
				var _refresh_ifram = refresh_tab.find('iframe')[0];
				var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;   
				//_refresh_ifram.src = refresh_url;   
				 _refresh_ifram.contentWindow.location.href=refresh_url;
			}
		}  
		
function addTab1(title, href){   
	var tt = parent.$('#tabs');   
	var h=$(tt).height()-31;
	if (tt.tabs('exists', title)){           
		tt.tabs('select', title);   
		refreshTab1({tabTitle:title,url:href});   
	} else {   
		if (href){   
			var content = '<iframe class="iframe" scrolling="yes" frameborder="0" src="'+href+'" style="width:100%;height:100%;"></iframe>';
		} else {   
			var content = 'error';   
		}   
		tt.tabs('add',{   
			title:title,   
			closable:true,   
			content:content,   
			iconCls:'icon-default'  
		});   
	}   
}   
function refreshTab1(cfg){   
	var refresh_tab = cfg.tabTitle?parent.$('#tabs').tabs('getTab',cfg.tabTitle):parent.$('#tabs').tabs('getSelected');   
	if(refresh_tab && refresh_tab.find('iframe').length > 0){   
		var _refresh_ifram = refresh_tab.find('iframe')[0];   
		var refresh_url = cfg.url?cfg.url:_refresh_ifram.src;
		_refresh_ifram.contentWindow.location.href=refresh_url;
	}   
}  
function changesize(wid,hei){
	var width  = parent.$('#tabs').width();
	var height = parent.$('#tabs').height();
	alert(width);
	if($('#changesize')){
		$('#changesize').datagrid({
			width:width-wid,
			height:height-hei
		});
	}
	if($('#dgitem'))
	{
		$('#dgitem').datagrid({
			width:width-wid,
			height:hei
		});
	}
}
function buttonstatus(){
	if(bAdd){
		$("#addtd").hide();
		$("#savetd").show();
	}else{
		$("#addtd").show();
		$("#savetd").hide();
	}
}