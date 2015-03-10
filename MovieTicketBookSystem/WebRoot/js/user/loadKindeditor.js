/**
 * 加载KindEditor编辑器
 */
KindEditor.ready(function(K) {		
	var editor1 = K.create('textarea[name="content"]', {			
		cssPath : '../../business/kindeditor/plugins/code/prettify.css',			
		uploadJson : '../../js/business/kindeditor/jsp/upload_json.jsp',			
		fileManagerJson : '../../js/business/kindeditor/jsp/file_manager_json.jsp',			
		allowFileManager : true,			
		afterCreate : function() {			
			var self = this;				
			K.ctrl(document, 13, function() {				
				self.sync();					
				document.forms['example'].submit();
				
			});				
			K.ctrl(self.edit.doc, 13, function() {				
				self.sync();
				document.forms['example'].submit();			
			});			
		}			
	});			
	prettyPrint();		
});