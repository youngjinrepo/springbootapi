# springbootapi

<h2>소개</h2>
<P>학습 내용을 정리하기 위해 만든 RESTful API</P>

<h2>사용 기술</h2>
<P>Spring Boot, JPA, QueryDsl, H2database, gradle</P>

<h2>서비스 정의</h2>

<h3>이용자</h3>
<table>
  <thead>
    <tr>
      <td>Method</td>
      <td>URL</td>
      <td>Description</td>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>GET</td>
      <td>/member/{id}</td>
      <td>id로 이용자 정보 조회</td>
    </tr>
    <tr>
      <td>POST</td>
      <td>/member/{id}</td>
      <td>이용자 정보 입력</td>
    </tr>
    <tr>
      <td>GET</td>
      <td>/member/{id}/address</td>
      <td>id로 이용자 주소 정보 조회</td>
    </tr>
    <tr>
      <td>POST</td>
      <td>/member/{id}/address</td>
      <td>이용자 주소 정보 입력</td>
    </tr>
  </tbody>
</table>
