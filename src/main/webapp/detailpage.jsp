<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="">
    </head>
    <body>
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
        />
         <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/datepicker.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-element-bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>


     
        <header 
            class="fixed flex justify-between p-5 w-full bg-white z-50 border-2 border-black-100 items-center"
        >   
            <button type="submit" onclick=location.href="mainpage.jsp" class="ml-5">
                <span class="text-4xl text-[#74cfca] font-bold">CAMP</span> <span class="text-2xl">*</span> <span class="text-4xl text-[#74cfca] font-bold">SCORE</span>
            </button>
            
   <form name="searchdata" action="searchpage.jsp" method="get">         
   <input type = "hidden" name = "name" value=""></input>
        <div class="flex justify-start mr-48">   
            <div class="flex items-center relative">
                <div class="relative">
                  <input name="start" id="datepicker"
                         type="text"  
                         class="flex items-center bg-gray-50 border border-gray-300 text-gray-900 text-sm focus:ring-blue-500 
                                                        focus:border-blue-500 block w-full ps-10 p-2.5 mt-5"  
                            placeholder="">
                </div>
                <div class="relative items-center relative">
                  <input name="end" id="datepicker2" type="text"  class=" flex items-center bg-gray-50 border border-gray-300 text-gray-900 text-sm focus:ring-blue-500 
                                                        focus:border-blue-500 block w-full ps-10 p-2.5 mt-6	" 
                            placeholder="">
              </div>
              <div class="flex items-center relative">
                <select id="campingRegion" name="campregion" type="text"  
                class="bg-gray-50 border rounded-r-lg border-gray-300 
                		text-gray-900 text-sm  focus:ring-blue-500 
                		focus:border-blue-500 block w-full ps-10 p-2.5"
                                        placeholder="지역">
                                <option value="" selected>지역</option>
                                <option value="경기">경기도</option>
                                <option value="강원">강원도</option>
                                <option value="충남">충청남도</option>
                                <option value="충북">충청북도</option>
                                <option value="전남">전라남도</option>
                                <option value="전북">전라북도</option>
                                <option value="경남">경상남도</option>
                                <option value="경북">경상북도</option>
                                <option value="제주">제주</option>
                            </select>
            </div> 
            <input type = "hidden" name = "camptype" value=""></input>
              </div>
              <div class= "flex relative items-center">   
                <button id="headerSearchButton" type="submit" onclick=location.href="searchpage.jsp" class="bg-[#eeeeee] hover:bg-[#dddddd] 
                                                                w-10 h-10    rounded-full
                                                                ">
                    <svg viewBox="-5 -5  36 36" fill="none" xmlns="http://www.w3.org/2000/svg" class="">
                        <path d="M15.7955 15.8111L21 21M18 10.5C18 14.6421 14.6421 18 10.5 18C6.35786 18 3 14.6421 3 10.5C3 6.35786 6.35786 3 10.5 3C14.6421 3 18 6.35786 18 10.5Z"
                        stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                                                        </svg> 
                </button>
            </div>
        </div>  
      </form>                                                            
            <div>
            </div>
        </header>
        <div class="pt-40 z-10">
            <div>
                <div id="camp-list">                                               
                </div>
                    
           </div>
                <div class="h-auto px-5">
                    <div class="flex flex-col 2xl:flex-row w-auto relative rounded-lg relative mx-5  px-10 border-b-2 border-black-100">
                        <div class="basis-3/5 p-5 content-center">
                            <img class="flex h-4/5 w-full relative rounded-lg mr-20 " src="images/gapyung67.webp" >
                        </div>
                
                        <div class="grid grid-cols-2 gap-5 relative basis-2/5 content-center">
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-5.jpeg" alt="">
                            </div>
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-2.jpeg" alt="">
                            </div>
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-3.jpeg" alt="">
                            </div>
                            <div>
                                <img class="rounded-lg" src="images/gapyung67-4.jpeg" alt="">
                            </div>
                        </div>
                     </div>
                </div>
            
            <div class="px-10">
                <div class="py-10 font-bold text-4xl px-20 border-b-2 border-black-100 ">
                    캠핑장 소개
                    <div class="font-medium text-sm py-5">
                        프라이빗 계곡과 독채 럭셔리 글램핑을 즐길 수 있는 캠핑장입니다
                        히노끼 월풀 스파, 영화관 객실, 안마의자 등 즐길 거리가 갖춰져 있습니다
                    </div>
                </div>
            <div class="relative w-full px-10 border-b-2 border-black-100 py-5">
                <div class="flex  h-80 w-10/12  bg-[#ffffff]  z-30 flex py-5 rounded-lg justify-center">
                            <div id="weather-list" class="flex flex-row justify-center">
                            </div>
                            
                </div>
            </div>   
            </div>
            <div class="py-10 font-bold text-md px-20  ">
	            	<label for="recmdTour" class="block mb-2 text-4xl font-bold text-gray-900 dark:text-white px-10 py-5" >추천 관광지</label>
                   <div class="grid md:grid-cols-4 gap-4 px-20">
                       <div>
                           <img class="h-auto max-w-full rounded-lg" src="images/경기/tour1.jpg" alt="">
                           <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">화담숲</label>
                       </div>
                       <div>
                           <img class="h-auto max-w-full rounded-lg" src="images/경기/tour2.jpg" alt="">
                           <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">포천아트밸리</label>
                       </div>
                       <div>
                        <img class="h-auto max-w-full rounded-lg" src="images/경기/tour3.jpg" alt="">
                           <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">수원화성</label>
                       </div>
                       <div>
                           <img class="h-auto max-w-full rounded-lg" src="images/경기/tour4.jpg" alt="">
                           <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">아침고요수목원</label>
                       </div>
                   </div>
              </div>    
            
            
            
                                                         
        <script>
 	var urldata = new Array();
    const regiondt = "";   
        function param() {
        	   var url = window.location.href;
        	   if(url.indexOf("?") > -1) {

        	      var splits = url.split("?"); //split("구분자"):tokenizer와 다른점은 split는 공백도 하나의 값을 가진다.
        	      var gets = splits[1];
        	      var para = gets.split("&");
        	      var len  = para.length;
				  
        	      
        	      for(var i=0; i<len; i++) {
        	         var param = para[i].split("=");
                	 var urlname  = param[0];
                	 var urlvalue = param[1];
                	 urldata[i] = urlvalue;
        	   }
        	        
        	}
        }
        param();
      	console.log(urldata);
      	
      	const cname = urldata[2];
      	let weatherScoreArray = new Array();
		let sum = 0;
    	let weatherScoreArray_avg =0;
			
      	async function getWeatherByRegion(){
        	const res = await fetch(`http://localhost:80/get/weather?region=${"${urldata[3]}"}`);
        	const resJson = await res.json();
        	const weathers = Object.values(resJson);
        	
        	 const data = [
                 {
                	 weather: resJson.wc0,
                     temp:resJson.tp0,
                     prec:resJson.rp0,
					 rain:resJson.wcd0,
                 },
                 {
                	 weather: resJson.wc1,
                     temp: resJson.tp1,
                     prec:resJson.rp1,
                     rain:resJson.wcd1,
                 },
                 {
                	 weather: resJson.wc2,
                     temp:resJson.tp2,
                     prec:resJson.rp2,
                     rain:resJson.wcd2,
                 },
                 {
                	 weather: resJson.wc3,
                     temp: resJson.tp3,
                     prec:resJson.rp3,
                     rain:resJson.wcd3,
                 },
                 {
                	 weather: resJson.wc4,
                     temp: resJson.tp4,
                     prec:resJson.rp4,
                     rain:resJson.wcd4,
                 },
                 {
                	 weather: resJson.wc5,
                     temp: resJson.tp5,
                     prec:resJson.rp5,
                     rain:resJson.wcd5,
                 },
                 {
                     weather: resJson.wc6,
                     temp: resJson.tp6,
                     rain:resJson.wcd6,
                     prec:resJson.rp6,
 
                 },
                 {
                     weather: resJson.wc7,
                     temp:resJson.tp7,
                     prec:resJson.rp7,
                     rain:resJson.wcd7,
                 }
             ];
        	 const weatherListTag = document.getElementById("weather-list");
             let innerHTML = '';
             var datetoday = new Date();
             var startdate = datetoday;
             var month = ('0' + (datetoday.getMonth() + 1)).slice(-2);
             var date = ('0' + datetoday.getDate()).slice(-2);
             
             var arrDayStr = ['일','월','화','수','목','금','토']; 
             var today = datetoday.getDay()
             let zerodate = 0;
             const result_date0 = Number(urldata[0])+1;
             const result_date = Number(urldata[1])+1;
             
             for(let i = result_date0; i < result_date0+result_date; i++){
            	 
        		const weatherData = data[i];
        		const precarray = (weatherData.prec).split('|',2);
        		const temparray = (weatherData.temp).split('|',2);
        		
        		const temp = temparray[0];
        		const temp2 = temparray[1];
            	const prec = temparray[0];
            	const prec2 = temparray[1];
            	const rainarray = (weatherData.rain).split('|',2);
            	const rain = rainarray[0];
            	const rain2 = rainarray[1];
            	const weatherarray = (weatherData.weather).split('|',2);
            	const weather1 = (rain == "WB09") ? rain :weatherarray[0];
            	const weather2 = (rain2 == "WB09") ? rain2 :weatherarray[1]
            	const imgSrc = getImgSrc(weather1);
        		const imgSrc2 = getImgSrc(weather2);
        		
        		
        		
            	var dt = ("0" + (datetoday.getDate()+i)).slice(-2);
            	var arraydate = today+i;
      			
            	var td = (arraydate > 6) ? zerodate++ : arraydate;
            	
            	var weatherscore = (weather1 == "WB00") ? 9 :
            					   (weather1 == "WB01") ? 9 :
            					   (weather1 == "WB02") ? 8 : 
            				       (weather1 == "WB03") ? 7.5 : 
            				       (weather1 == "WB04") ? 7 : 
            				       (weather1 == "WB09") ? 5 : 
            				        "error" ;
            	weatherscore =(weatherscore == "error") ? "error" : 
            			      (weather2 == "WB00") ? weatherscore+1 :
            			      (weather2 == "WB01") ? weatherscore+1 :
            			      (weather2 == "WB02") ? weatherscore : 
            			      (weather2 == "WB03") ? weatherscore -0.5 : 
            			      (weather2 == "WB04") ? weatherscore -1 : 
            			      (weather2 == "WB09") ? weatherscore -3: 
            			       error ;
        
	       		console.log(i+"일차"+weatherscore);
				weatherScoreArray[i] = weatherscore;
	      		
	      		
          	 	innerHTML += ` 
               		
          	 		<div class="flex flex-row  h-full w-32 mr-10 pt-10 items-center justify-center ">
            		<div class="flex flex-col w-32">
		               <label for="" class="block text-lg font-bold text-gray-900 mx-auto"><span class="mx-auto text-xl"><span class="flex justify-center">${"${arrDayStr[td]}"}</span><br>${"${month}.${dt}"}</label>
		                <div class="h-auto w-auto flex flex-row items-center mt-5 justify-center ">
		                    <img src=${"${imgSrc}"} class="w-16 h-16">
		                    <img src=${"${imgSrc2}"} class="w-16 h-16">
		                </div>
		                <div class="flex flex-row mx-auto items-center mt-3 justify-center">
		                    <span class="text-[#45A6FF] font-md text-2xl">${"${temp}"}°</span>
		                    <span class="text-[#959FA9] text-2xl">/</span>
		                    <span class="text-[#F42E2E] font-bold text-2xl">${"${temp2}"}°</span>
		                </div>
		                <div class="flex flex-row mx-auto items-center mt-5 font-bold justify-center">
		                    <span class="text-[#3B9DE3] mr-2">${"${prec}%"}</span>
		              	      <span class="text-[#3B9DE3]">${"${prec2}%"}</span>
		                </div>
	                </div>
	             </div>
            `;
              
        	 }
             for(let i = 0; i < weatherScoreArray.length; i++){
       		 	if(weatherScoreArray[i] != null){
  	      			sum += weatherScoreArray[i];
  	      			}
  	      		}
              
  	      		var avg = (sum/result_date);
  	      		weatherScoreArray_avg = Math.round(avg * 100) / 100;
  	      		console.log(weatherScoreArray);
             
 	      		
             	
                weatherListTag.innerHTML = innerHTML;

                function getImgSrc(weather){	
                    switch(weather){
                    	case 'WB01':
                    		return 'images/sunny.png';
                    		break;
                    	case 'WB02':
                    		return 'images/partly-cloudy.png';
                    		break;
                    	case 'WB03':
                    		return 'images/cloudy(2).png';
                    		break;
                    	case 'WB04':
                    		return 'images/cloudy.png';
                    		break;
                    	case 'WB09':
                    		return 'images/rain.png';
                    		break;
                        default :
                            return 'images/raindrop.png';
                 	}
                    
                }
                
                function getImgSrc2(weather2){	
                    switch(weather2){
                    	case 'WB01':
                    		return 'images/sunny.png';
                    		break;
                    	case 'WB02':
                    		return 'images/partly-cloudy.png';
                    		break;
                    	case 'WB03':
                    		return 'images/cloudy(2).png';
                    		break;
                    	case 'WB04':
                    		return 'images/cloudy.png';
                    		break;
                        default :
                            return 'images/raindrop.png';
                 	}
                    
                }
      		
                }
			
  		
  		
		async function campdata() {
				 
        	
        const cres = await fetch(`http://localhost:80/get/campinglist?place_name=${"${urldata[2]}"}`);
        const campJson = await cres.json();
        
        
    	const campdata = [
        	{
        		placeaddress:campJson.item0.addressName,
              	placeid:campJson.item0.placeID,
              	placename:campJson.item0.placeName,
		        placeurl:campJson.item0.placeUrl,
       			placecategory: campJson.item0.placeCategoryDetail,
                placeregion:campJson.item0.region,
      
        	}
    	];
        const campListTag = document.getElementById("camp-list");
        const campData = campdata[0];
        const regiondt = campData.placeregion;
        			
      
		            const innerHTML = ` 
	            
	            	<form name="detaildata" action="detailpage.jsp" method="get">
	            	</form>
			            	<div class="flex flex-col w-auto h-auto px-5 py-5 border-b-2 border-black-100 ">
			                <div class="flex flex-row place-content-between">
			                    <div class="flex flex-col">  
			                        <div class="text-sm">
			                            ${"${campData.placeregion}"}/${"${campData.placecategory}"}
			                        </div>
			                        <div class="mt-1  font-bold text-4xl">
			                            ${"${campData.placename}"}
			                        </div>
			                        <div class="text-sm font-sm mb-10">
			                        	${"${campData.placeaddress}"}
			                        </div>
			                         <div>
			                        <button type="button" onclick ="window.open('${"${campData.placeurl}"}')" 		
				                    	class= "flex text-white end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-sm rounded-lg text-sm px-4 py-2" >url</button>
			                    	</div>
			                    </div> 
			                   
			                    <div>
			                         <div class="text-4xl font-bold">
			                            총날씨점수
			                        </div>
			                        <div class="text-8xl items-center flex justify-center">
			                        	${"${weatherScoreArray_avg}"}		
				                    </div>                                                
			                </div>
			                
			            </div>
        `;
        		console.log(weatherScoreArray_avg);
	            campListTag.innerHTML = innerHTML;
	           
	            
		}     
           
          		

      	campdata();
      	$(function() {
 	       //input을 datepicker로 선언
 	       $("#datepicker").datepicker({
 	           dateFormat: 'yy-mm-dd' //달력 날짜 형태
 	           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
 	           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
 	           ,changeYear: true //option값 년 선택 가능
 	           ,buttonText: null
 	           ,changeMonth: true //option값  월 선택 가능                
 	           ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
 	           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
 	           ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
 	           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
 	           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
 	           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
 	           ,minDate: "0D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
 	           ,maxDate: "+7D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
 	       });                    
 	       
 	       //초기값을 오늘 날짜로 설정해줘야 합니다.
 	       $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
 	   });
 		
 	 $(function() {
	       //input을 datepicker로 선언
	       $("#datepicker2").datepicker({
	           dateFormat: 'yy-mm-dd' //달력 날짜 형태
	           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
	           ,buttonText: null
	           ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
	           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
	           ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
	           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
	           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
	           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
	           ,minDate: "0D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	           ,maxDate: "+7D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
	       });                    
	       
	       //초기값을 오늘 날짜로 설정해줘야 합니다.
	       $('#datepicker2').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
	   });
 	getWeatherByRegion()
        </script>
    </body>
</html>