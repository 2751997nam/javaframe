<% int page_number = Integer.parseInt(request.getAttribute("page_number").toString()); %>
<% int current = Integer.parseInt(request.getAttribute("current").toString()); %>
<% String[] links = (String[]) request.getAttribute("links"); %>
<% String url = request.getAttribute("url").toString();%>
<ul class="pagination">
    <li class="page-item"><a class="page-link" href="<%= links[0] %>">First</a></li>
    <li class="page-item"><a class="page-link" href="<%= links[1] %>"> « </a></li>
    <% if(page_number < 6) {%>
        <% for (int i = 1; i <= page_number; i++) {%>
            <li class="page-item  <%= i == current ? "active" : ""%>"><a class="page-link" href="<%= url + i%>"><%= i%></a></li>
        <% }%>
    <% } else {%>
        <% if(current <= 5 ) {%>
            <% for (int i = 1; i <= 5; i++) {%>
                <li class="page-item  <%= i == current ? "active" : ""%>"><a class="page-link" href="<%= url + i%>"><%= i%></a></li>
            <% }%>
            <li class="page-item"><a class="page-link" href="javascript:void(0)">...</a></li>
        <% } else if(current < page_number - 4) {%>
                <li class="page-item"><a class="page-link" href="javascript:void(0)">...</a></li>
                <li class="page-item"><a class="page-link" href="<%= url + (current - 2) %>"><%= current - 2 %></a></li>
                <li class="page-item"><a class="page-link" href="<%= links[1] %>"><%= current - 1 %></a></li>
                <li class="page-item active"><a class="page-link" href="<%= links[2] %>"><%= current%></a></li>
                <li class="page-item"><a class="page-link" href="<%= links[3] %>"><%= current + 1 %></a></li>
                <li class="page-item"><a class="page-link" href="<%= url + (current + 2) %>"><%= current + 2 %></a></li>
                <li class="page-item"><a class="page-link" href="javascript:void(0)">...</a></li>
            <% } else {%>
                    <li class="page-item"><a class="page-link" href="javascript:void(0)">...</a></li>  
                    <% for (int i = page_number - 4; i <= page_number; i++) {%>
                        <li class="page-item  <%= i == current ? "active" : ""%>"><a class="page-link" href="<%= url + i%>"><%= i%></a></li>
                    <% }%>              
                <% } %>
    <% } %>
    <li class="page-item"><a class="page-link" href="<%= links[3] %>"> » </a></li>
    <li class="page-item"><a class="page-link" href="<%= links[4] %>">Last</a></li>
</ul>
