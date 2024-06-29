<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<script type="text/javascript">

</script>

<!-- 分页 -->
<div style="margin: 0 auto; margin-top: 50px; text-align: center;">
	<ul class="pagination" style="margin-top: 10px;">
		<c:if test="${pageBean.currentPage==1}">
			<li class="disabled">
				<a href="javascript:void(0);" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
		</c:if>
		<c:if test="${pageBean.currentPage!=1}">
			<li>
				<a href="${pageContext.request.contextPath}/product?method=product_list&cid=${cid}&currentPage=${pageBean.currentPage-1}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
				</a>
			</li>
		</c:if>

		<!-- 使用forEach循环生成所有页码的链接 -->
		<c:forEach begin="1" end="${pageBean.totalPage}" varStatus="page">
			<!-- 显示前两页和后两页的链接，以及当前页的链接) -->
			<!-- 计算一下当前页前面需要展示的页数 和 计算一下当前页后面需要展示的页数 但是保证页数总合为 4 -->

			<c:if test="${page.count == 1 || page.count == pageBean.totalPage || (page.count >= pageBean.currentPage - 2 && page.count <= pageBean.currentPage + 2)}">
				<c:if test="${pageBean.currentPage==page.count}">
					<li class="active"><a href="javascript:void(0);">${page.count}</a></li>
				</c:if>
				<c:if test="${pageBean.currentPage!=page.count}">
					<li><a href="${pageContext.request.contextPath}/product?method=product_list&cid=${cid}&currentPage=${page.count}">${page.count}</a></li>
				</c:if>
			</c:if>
			<!-- 添加省略号 -->
			<c:if test="${page.count == pageBean.currentPage - 3 && pageBean.currentPage - 3 > 1}">
				<li><a href="javascript:void(0);">...</a></li>
			</c:if>
			<c:if test="${page.count == pageBean.currentPage + 3 && pageBean.currentPage + 3 <= pageBean.totalPage}">
				<li><a href="javascript:void(0);">...</a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageBean.currentPage==pageBean.totalPage}">
			<li class="disabled">
				<a href="javascript:void(0);" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</c:if>
		<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
			<li>
				<a href="${pageContext.request.contextPath}/product?method=product_list&cid=${cid}&currentPage=${pageBean.currentPage+1}" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
				</a>
			</li>
		</c:if>
	</ul>
</div>