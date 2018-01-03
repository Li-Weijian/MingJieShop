<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		
		<!-- 弹出层插件 -->
		<link href="${pageContext.request.contextPath}/css/popup_layer.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/popup_layer.js"></script>		
		<!-- 调用插件弹出层的方法 -->
		<script type="text/javascript">
			$(function(){
				//弹出层插件调用
				new PopupLayer({
					trigger:".clickedElement",
					popupBlk:"#showDiv",
					closeBtn:"#closeBtn",
					useOverlay:true
				});
				
			});
			
			
			
		</script>
		
	</HEAD>
	<body>
	
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="10%">
										订单编号
									</td>
									<td align="center" width="10%">
										订单金额
									</td>
									<td align="center" width="10%">
										收货人
									</td>
									<td align="center" width="10%">
										订单状态
									</td>
									<td align="center" width="50%">
										订单详情
									</td>
								</tr>
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">
										1
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">
										12345
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">
										300
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">
										zhangsan
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%">
										已付款
									</td>
									<td align="center" style="HEIGHT: 22px">
										<input type="button" value="订单详情" class="clickedElement" onclick="findOrderInfoByOid('fc86891e-5126-452e-932d-c4fe382ba73f')"/>
									</td>
					
								</tr>
								
							</table>
						</td>
					</tr>
					
				</TBODY>
			</table>
		</form>
		
		<!-- 弹出层 HaoHao added -->
        <div id="showDiv" class="blk" style="display:none;">
            <div class="main">
                <h2>订单编号：<span id="shodDivOid" style="font-size: 13px;color: #999">123456789</span></h2>
                <a href="javascript:void(0);" id="closeBtn" class="closeBtn">关闭</a>
                <div id="loading" style="padding-top:30px;text-align: center;">
                	<img alt="" src="${pageContext.request.contextPath }/images/loading.gif">
                </div>
				<div style="padding:20px;">
					<table id="showDivTab" style="width:100%">
						<tr id='showTableTitle'>
							<th width='20%'>图片</th>
							<th width='25%'>商品</th>
							<th width='20%'>价格</th>
							<th width='15%'>数量</th>
							<th width='20%'>小计</th>
						</tr>
						<tr style='text-align: center;'>
							<td>
								<img src='${pageContext.request.contextPath }/products/1/c_0014' width='70' height='60'>
							</td>
							<td><a target='_blank'>电视机</a></td>
							<td>￥3000</td>
							<td>3</td>
							<td><span class='subtotal'>￥9000</span></td>
						</tr>
						<tr style='text-align: center;'>
							<td>
								<img src='${pageContext.request.contextPath }/products/1/c_0014' width='70' height='60'>
							</td>
							<td><a target='_blank'>电视机</a></td>
							<td>￥3000</td>
							<td>3</td>
							<td><span class='subtotal'>￥9000</span></td>
						</tr>
						
						
					</table>
				</div>
            </div>
            
        </div>
		
		
	</body>
</HTML>

