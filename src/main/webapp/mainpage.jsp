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
            class="fixed flex justify-between p-5 w-full bg-white z-50"
        >
            <div>
            	<button type="submit" onclick=location.href="mainpage.jsp" class="ml-5">
                <span class="text-4xl text-[#74cfca] font-bold">CAMP</span> <span class="text-2xl">*</span> <span class="text-4xl text-[#74cfca] font-bold">SCORE</span>
                </button>
            </div>
        </header>

        <div class="pt-20 z-10">    
            <!-- 이미지 슬라이드 / 검색 -->
            <div class="w-full h-4/5">
                <div class="swiper">
                    <swiper-container class="mySwiper" pagination="true" pagination-clickable="true" navigation="true" space-between="0"
                    centered-slides="true" autoplay-delay="5000" autoplay-disable-on-interaction="false">
                        <swiper-slide>
                            <img src="images/제주/cam3.jpg" class="w-full">
                        </swiper-slide>
                        <swiper-slide>
                            <img src="images/경북/cam4.jpg" class="w-full">
                        </swiper-slide>
                        <swiper-slide>
                            <img src="images/경기/cam2.jpg" class="w-full">
                        </swiper-slide>
                        <swiper-slide>
                            <img src="images/main1.jpg" class="w-full">
                        </swiper-slide>
                        <swiper-slide>
                            <img src="images/main2.jpg" class="w-full">
                        </swiper-slide>
                        <swiper-slide>
                            <img src="images/main3.jpg" class="w-full">
                        </swiper-slide>
                    </swiper-container>
                </div>
                
             <form name="searchdata" action="searchpage.jsp" method="get">
                <div class="relative w-3/5 -translate-y-1/2 mx-auto bg-[#ffffff] border-2 border-black-100 z-30 flex justify-center p-10">
                    <form class="flex justify-center flex-col 2xl:flex-row gap-5 w-full" action="" onsubmit="">
                        <input 
                            type="text"
                            id="campNameSearch"
                            name="name"
                            placeholder="이름"
                            class="bg-[#E8E8E8] text-gray-900 rounded-lg p-5 focus:ring-blue-500 focus:border-blue-500"
                        >

                        <div class="flex w-full">
                            <input 
                                id="datepicker" 
                                name="start" 
                                type="text" 
                                class="
                                    w-1/2
                                    bg-[#E8E8E8] rounded-l-lg 
                                    text-gray-900 text-sm 
                                    focus:ring-blue-500 focus:border-blue-500 p-5 text-right
                                " 
                                placeholder="날짜"
                            >

                            <input 
                                id="datepicker2"
                                name="end"
                                type="text"
                                class="
                                w-1/2
                                    bg-[#E8E8E8] rounded-r-lg 
                                    text-gray-900 text-sm  
                                    focus:ring-blue-500 focus:border-blue-500 p-5 text-left
                                " 
                                placeholder="날짜"
                            >
                        </div> 
    
                        <div class="grid grid-cols-2 w-full">
                            <select id="campingRegion" name="campregion" type="text" class="bg-[#E8E8E8] rounded-lg  
                                        border  border-gray-300 text-gray-900 text-sm  focus:ring-blue-500 
                                        focus:border-blue-500 p-5 dark:bg-gray-700"
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

                            <select id="campingType" name="camptype" type="text" class="bg-[#E8E8E8] rounded-lg
                                        border  border-gray-300 text-gray-900 text-sm  focus:ring-blue-500 
                                        focus:border-blue-500 block w-30 p-5"
                                                    placeholder="종류">
                                <option value="" selected>종류</option>
                                <option value="오토캠핑장">오토캠핑장</option>
                                <option value="카라반">카라반</option>
                                <option value="글램핑">글램핑</option>
                            </select> 
                        </div>
                        
                        <div>
                            <button type="submit" location.href="searchpage.jsp" class="bg-[#eeeeee] hover:bg-[#dddddd] w-full 2xl:w-16 h-16 rounded-full">
                                <svg viewBox="-5 -5  36 36" fill="none" xmlns="http://www.w3.org/2000/svg" class="hidden 2xl:inline">
                                    <path d="M15.7955 15.8111L21 21M18 10.5C18 14.6421 14.6421 18 10.5 18C6.35786 18 3 14.6421 3 10.5C3 6.35786 6.35786 3 10.5 3C14.6421 3 18 6.35786 18 10.5Z"
                                    stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                </svg>
                                <span class="inline 2xl:hidden text-center">검색</span>
                            </button>
                        </div>
                        
                    </form>
                </div> 
                
	                <div name="선호지역" type="text" class="h-32 w-8/12 justifly-center relative flex my-auto mx-auto">
			                <select id="selectRegion" class="w-1/4 1/4 items-center text-2xl font-bold" onChange="getWeatherByRegion(this.value)">
			                                <option value="경기" selected>경기도</option>
			                                <option value="강원">강원도</option>
			                                <option value="충북">충청남도</option>
			                                <option value="충남">충청북도</option>
			                                <option value="전남">전라남도</option>
			                                <option value="전북">전라북도</option>
			                                <option value="경남">경상남도</option>
			                                <option value="경북">경상북도</option>
			                                <option value="제주">제주</option>
			                               
							</select>
							</div>             
           				 </div>
        			   </div>
					 <div>
				</div>
			
     

        
            <!-- 날씨 -->
            <div class="relative w-full">
                <div class="  h-96 w-10/12 mx-auto bg-[#ffffff] border-2 border-black-100 z-30 py-5 rounded-lg px-5 flex justify-center ">
                            <div id="weather-list" class="flex flex-row">     
                            </div>
                </div>
            </div>
            <!-- 추천 캠핑장 / 추천 관광지 -->
            <div id="tour-list">
                
        </div>
        <script>
            function campdate() {
            const startdate = document.getElementById("startDate").value;
            
            }
            
            function search1() {
               const campname = document.getElementById("campNameSearch").value;
               const campregion = document.getElementById("campingRegion").value;
               const camptype = document.getElementById("campingType").value; 
               alert(campname);
            }
            function regiondata(){
              const defaultRegionData = document.getElementById("selectRegion").value;
              getWeatherByRegion(defaultRegionData);
            }
            regiondata();
            
            
            async function getWeatherByRegion(value){
				
				const changeregion = value;
				console.log(changeregion);
            	const res = await fetch(`http://localhost:80/get/weather?region=${"${value}"}`);
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
                 
            	 for(let i = 0; i<8;i++){
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
                	
                	var weatherscore = (weather1 == "WB01") ? 9 :
                					   (weather1 == "WB02") ? 8 : 
                				       (weather1 == "WB03") ? 7.5 : 
                				       (weather1 == "WB04") ? 7 : 
                				       (weather1 == "WB09") ? 5 : 
                				        "error" ;
                	weatherscore =(weatherscore == "error") ? "error" : 
                			      (weather2 == "WB01") ? weatherscore+1 :
                			      (weather2 == "WB02") ? weatherscore : 
                			      (weather2 == "WB03") ? weatherscore -0.5 : 
                			      (weather2 == "WB04") ? weatherscore -1 : 
                			      (weather2 == "WB09") ? weatherscore -3: 
                			       error ;
            
    	       		console.log(i+"일차"+weatherscore);
    	       		
    	       		
              	 	innerHTML += ` 
                   		
        	            <div class="flex flex-row  h-full w-32 mr-10 items-center justify-center ">
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
                    
                    function tourChange(){
            		
                    	 const cpnamedata = [
                             {
                            	 camp1:"청계산골든밸리글램핑&캠핑장",
                            	 camp2:"광릉해오름캠핑장",
                            	 camp3:"자라섬캠핑장",
                                 camp4:"그라티아글램핑",
                             },
                             {
                            	 camp1:"동강전망휴양림오토캠핑장",
                            	 camp2:"홍천 어울림 글램핑펜션",
                            	 camp3:"행복휴양림&아우라지글램핑",
                                 camp4:"홍천강오토캠핑장",
                             },
                             {
                            	 camp1:"경상북도포항산누리오토캠핑장",
                            	 camp2:"생림 오토캠핑장",
                            	 camp3:"80 SS 오토캠핑장",
                                 camp4:"위정약수오토캠핑장",
                             },
                             {
                            	 camp1:"김녕해수욕장야영장",
                            	 camp2:"대정읍 화순 해수욕장",
                            	 camp3:"비양도 연평리 야영지",
                                 camp4:"돌하르방 캠핑장",
                             }
                    	 ];	 
                    	
                    	 const tournamedata = [
                             {
                            	 tour1:"화담숲",
                            	 tour2:"포천아트밸리",
                            	 tour3:"수원화성",
                                 tour4:"아침고요수목원",
                             },
                             {
                            	 tour1:"남이섬",
                            	 tour2:"추암 촛대바위",
                            	 tour3:"대관령양떼목장",
                                 tour4:"설악산국립공원",
                             },
                             {
                            	 tour1:"불국사",
                            	 tour2:"동궁과 월지",
                            	 tour3:"첨성대",
                                 tour4:"국립경주박물관",
                             },
                             {
                            	 tour1:"한라산",
                            	 tour2:"섭지코지",
                            	 tour3:"주상절리대",
                                 tour4:"만창굴",
                             }
                    	 ];	 
                    	 
                    	 function campImgSrc(cregion){	
                             switch(cregion){
                             	case '경기':
                             		return "0";
                             		break;
                             	case '강원':
                             		return '1';
                             		break;
                             	case '경북':
                             		return '2';
                             		break;
                             	case '제주':
                             		return '3';
                             		break;
                                 default : 
                                     return '1';
                          	}
                    	 }  
		            var cregion = (`${"${changeregion}"}`);
		            var tourListTag = document.getElementById("tour-list");	
		            var cam	= campImgSrc(cregion);
		            var cam_name = cpnamedata[cam];
		            var tour_name = tournamedata[cam];
		            console.log(cam_name.camp1); 
		            
		            
		            
		            innerHTML = ` 
		            	<label for="recmdCamp" class="block mb-2 text-4xl font-bold text-gray-900 dark:text-white px-10 pt-40 py-5">추천 캠핑장</label>
		                <div class="grid md:grid-cols-4 gap-4 px-20 ">
		                    <div>
		                        <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/camp1.jpg" alt="">
		                        <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${cam_name.camp1}"}</label>
		                    </div>
		                    <div>
		                        <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/camp2.jpg" alt="">
		                        <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${cam_name.camp2}"}</label>
		                    </div>
		                    <div>
		                        <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/camp3.jpg" alt="">
		                        <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${cam_name.camp3}"}</label>
		                    </div>
		                    <div>
		                        <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/camp4.jpg" alt="">
		                        <label for="campExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${cam_name.camp4}"}</label>
		                    </div>
		                </div>
	               		<div>
			            	<label for="recmdTour" class="block mb-2 text-4xl font-bold text-gray-900 dark:text-white px-10 pt-40 py-5" >추천 관광지</label>
		                    <div class="grid md:grid-cols-4 gap-4 px-20">
		                        <div>
		                            <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/tour1.jpg" alt="">
		                            <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${tour_name.tour1}"}</label>
		                        </div>
		                        <div>
		                            <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/tour2.jpg" alt="">
		                            <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${tour_name.tour2}"}</label>
		                        </div>
		                        <div>
		                            <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/tour3.jpg" alt="">
		                            <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${tour_name.tour3}"}</label>
		                        </div>
		                        <div>
		                            <img class="h-auto max-w-full rounded-lg" src="images/${"${changeregion}"}/tour4.jpg" alt="">
		                            <label for="tourpExp1" class="text-center block mb-2 text-md  font-bold text-gray-900 dark:text-white px-10 py-5">${"${tour_name.tour4}"}</label>
		                        </div>
		                    </div>
		               </div> 
	                  `;  
	                    
		            tourListTag.innerHTML = innerHTML;	
                    }
                    
                    tourChange();
                }
            	   $(function() {
            	       //input을 datepicker로 선언
            	       $("#datepicker").datepicker({
            	           dateFormat: 'yy-mm-dd' //달력 날짜 형태
            	           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
            	           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
            	           ,changeYear: true //option값 년 선택 가능
            	           ,changeMonth: true //option값  월 선택 가능 
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
            	       $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
            	   });
            		
            	 $(function() {
          	       //input을 datepicker로 선언
          	       $("#datepicker2").datepicker({
          	           dateFormat: 'yy-mm-dd' //달력 날짜 형태
          	           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
          	           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
          	           ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
          	           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
          	           ,buttonText: null
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
            	
	       </script>
    </body>
</html>