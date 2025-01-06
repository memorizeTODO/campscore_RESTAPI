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
    <body class="bg-[#F5F5F5]">
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
        />
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/2.3.0/datepicker.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-element-bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <header 
            class="fixed flex justify-between p-5 w-full bg-white z-50 border-2 border-black-100"
        >
            <button type="submit" onclick=location.href="mainpage.jsp" class="ml-5">
                <span class="text-4xl text-[#74cfca] font-bold">CAMP</span> <span class="text-2xl">*</span> <span class="text-4xl text-[#74cfca] font-bold">SCORE</span>
            </button>
        <div class="flex justify-start">   
            
            
        </div>                                                                 
        </header>
        <div class="pt-20 z-10">
        <!--날씨-->
            <div class="w-full h-1/4">
                <div class="items-center font-bold text-4xl text-center my-28">  
                </div>
            </div>
            <div class="relative w-full">
                   <div class="relative w-full">
                <div class="  h-96 w-10/12 mx-auto bg-[#ffffff] border-2 border-black-100 z-30 py-5 rounded-lg px-5 block">
                          <div id="weather-list" class="flex flex-row justify-center ">     
                       </div>
                </div>
            </div>
            </div>
            
           

            <div class="relative flex w-full">
         <!--태그영역-->
                <div class="mx-10 w-80 flex flex-col top-0">
                    <div class="py-10 rounded-lg ">
                        <form class="w-80">   
                            <label for="name_search" class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search</label>
                            <div class="relative">
                                <input type="search" id="name_search" onchange="nameinput(this.value)" class="block w-full p-4 text-sm text-gray-900
                                                                                 border border-gray-300 rounded-lg bg-white 
                                                                                 focus:ring-gray-500 focus:border-gay-500" placeholder="이름" required />
                                <button type="submit" onclick="nameinput()"class="text-white absolute end-2.5 bottom-2.5 
                                                             bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 
                                                             font-sm rounded-lg text-sm px-4 py-2">
                                    <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                                        <path stroke="white" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                                    </svg>
                                </button>
                            </div>
                        </form>
                    </div>

                    <form id="radioGroup">    
                        <h3 class="mb-4 font-semibold text-gray-900 dark:text-white">캠핑장 종류</h3>
                        <ul class="w-48 text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-white">
                            <li class="w-full border-b border-gray-200 rounded-t-lg dark:border-gray-600">
                                <div class="flex items-center ps-3">
                                    <input onc id="caravan-checkbox" type="radio" value="카라반" name="placeCategoryDetail" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded 
                                                                                             focus:ring-blue-500 dark:focus:ring-blue-600 ">
                                    <label for="caravan-checkbox" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">카라반</label>
                                </div>
                            </li>
                            <li class="w-full border-b border-gray-200 rounded-t-lg dark:border-gray-600">
                                <div class="flex items-center ps-3">
                                    <input id="gramping-checkbox" type="radio" value="글램핑" name="placeCategoryDetail" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded 
                                                                                               focus:ring-blue-500 dark:focus:ring-blue-600 ">
                                    <label for="gramping-checkbox" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">글램핑</label>
                                </div>
                            </li>
                            <li class="w-full border-b border-gray-200 rounded-t-lg dark:border-gray-600">
                                <div class="flex items-center ps-3">
                                    <input id="autocamping-checkbox" type="radio" value="오토캠핑장" name="placeCategoryDetail" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded 
                                                                                                 focus:ring-blue-500 dark:focus:ring-blue-600 ">
                                    <label for="autocamping-checkbox" class="w-full py-3 ms-2 text-sm font-medium text-gray-900">오토캠핑장</label>
                                </div>
                            </li>
                        </ul>
                  </form>  
                    
                </div>
                

        <!--검색목록-->
       		 
            <div class="flex flex-col w-full pb-5"> 
            <form action="" method="get" >
                <div class="flex relative flex-row-reverse w-10/12">
                                        <select id="sort_type" class="py-2.5 px-0 text-sm text-gray-500 bg-[#F5F5F5] 
                                        focus:outline-none focus:ring-0 focus:border-gray-200 peer" onChange="campdata()">
                                            <option value="asc"selected>오름차순</option>
                                            <option value="desc">내림차순</option>
                                            </select>  
                                        <select id="order" class="py-2.5 px-0 text-sm text-gray-500   bg-[#F5F5F5]
                                                                            focus:outline-none focus:ring-0 focus:border-gray-200 peer" 
                                                                            onChange="campdata()">
                                            <option value="place_name" selected>이름순</option>
                                             <option value="weather_score">날씨점수순</option>
                                           
                                        </select>
                                        
                    </div>
       				</form>
       
                <div id="camp-list" >
            </div>
          
                        
          </div>  
                </div>
            </div>  
       </div>
                










        <script charset="UTF-8">
        var urldata = new Array();
        
        
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
      console.log(urldata);
        param();
        Date.prototype.getInterval = function (otherDate) {
        	    var interval;
        	 
        	    if(this > otherDate)
        	        interval = this.getTime() - otherDate.getTime();
        	    else
        	        interval = otherDate.getTime() - this.getTime();
        	 
        	    return Math.floor(interval / (1000*60*60*24));
        	}
        
		var dt0 = new Date();	
        var dt1 = new Date(`${"${urldata[1]}"}`);
        var dt2 = new Date(`${"${urldata[2]}"}`);
        
        var result_date = (dt1.getInterval(dt2))+1;
        var result_date0 = (dt0.getInterval(dt1))+1;
        var regiondt = (`${"${urldata[3]}"}`);
        
		console.log(dt1);       
		console.log(dt2);  
		var categorydata = (`${"${urldata[4]}"}`);
        var campingname = (`${"${urldata[3]}"}`);
        
        const element = document.querySelector('#radioGroup');
        element.addEventListener('change', handleChange);
        function handleChange(event) {
        	  // 현재 선택 상태를 가져오기
        	  const detailTypeValue = element.placeCategoryDetail.value;
        	  
        	  categorydata = (`${"${detailTypeValue}"}`);
        	  campdata();
        	}
		
        function nameinput() {
        	campingname = document.getElementById("name_search").value;
        	campdata();
        }
		
        async function campdata() {
            var sortdata =	document.getElementById("sort_type").value;
			var orderdata = document.getElementById("order").value;
			var typedata = document.getElementById("order").value;
        	
			console.log(categorydata);
			
            const cres = await fetch(`http://localhost:80/get/campinglist?page=2&region=${"${campingname}"}&sort_type=${"${orderdata}"}&order=${"${sortdata}"}&placeName=${"${urldata[0]}"}&category=${"${categorydata}"}`);
            const campJson = await cres.json();
     		
			
     		const campingdata = [
                {
                	 placeaddress:campJson.item0.addressName,
	               	 placeid:campJson.item0.placeID,
	               	 placename:campJson.item0.placeName,
			         placeurl:campJson.item0.placeUrl,
	        		 placecategory: campJson.item0.placeCategoryDetail,
	                 placeregion:campJson.item0.region,
         
                },
                {
                	 placeaddress:campJson.item1.addressName,
                   	 placeid:campJson.item1.placeID,
                   	 placename:campJson.item1.placeName,
    		         placeurl:campJson.item1.placeUrl,
            		 placecategory: campJson.item1.placeCategoryDetail,
                     placeregion:campJson.item1.region,
                },
                {
                	 placeaddress:campJson.item2.addressName,
                   	 placeid:campJson.item2.placeID,
                   	 placename:campJson.item2.placeName,
    		         placeurl:campJson.item2.placeUrl,
            		 placecategory: campJson.item2.placeCategoryDetail,
                     placeregion:campJson.item2.region,
                },
                {
                	 placeaddress:campJson.item3.addressName,
                   	 placeid:campJson.item3.placeID,
                   	 placename:campJson.item3.placeName,
    		         placeurl:campJson.item3.placeUrl,
            		 placecategory: campJson.item3.placeCategoryDetail,
                     placeregion:campJson.item3.region,
                },
                {
                	 placeaddress:campJson.item4.addressName,
                   	 placeid:campJson.item4.placeID,
                   	 placename:campJson.item4.placeName,
    		         placeurl:campJson.item4.placeUrl,
            		 placecategory:campJson.item4.placeCategoryDetail,
                     placeregion:campJson.item4.region,
                },
                {
                	 placeaddress:campJson.item5.addressName,
                   	 placeid:campJson.item5.placeID,
                   	 placename:campJson.item5.placeName,
    		         placeurl:campJson.item5.placeUrl,
            		 placecategory: campJson.item5.placeCategoryDetail,
                     placeregion:campJson.item5.region,
                },
                {
                	placeaddress:campJson.item6.addressName,
                   	 placeid:campJson.item6.placeID,
                   	 placename:campJson.item6.placeName,
    		         placeurl:campJson.item6.placeUrl,
            		 placecategory: campJson.item6.placeCategoryDetail,
                     placeregion:campJson.item6.region,
                },
                {
                	 placeaddress:campJson.item7.addressName,
                   	 placeid:campJson.item7.placeID,
                   	 placename:campJson.item7.placeName,
    		         placeurl:campJson.item7.placeUrl,
            		 placecategory:campJson.item7.placeCategoryDetail,
                     placeregion:campJson.item7.region,

                },
                {
                	 placeaddress:campJson.item8.addressName,
                   	 placeid:campJson.item8.placeID,
                   	 placename:campJson.item8.placeName,
    		         placeurl:campJson.item8.placeUrl,
            		 placecategory: campJson.item8.placeCategoryDetail,
                     placeregion:campJson.item8.region,
                },
                {
                	 placeaddress:campJson.item9.addressName,
	                 placeid:campJson.item9.placeID,
	                 placename:campJson.item9.placeName,
	   		         placeurl:campJson.item9.placeUrl,
	           		 placecategory:campJson.item9.placeCategoryDetail,
	                 placeregion:campJson.item9.region,
               }
            ];
			const campdataListTag = document.getElementById("camp-list");
            let innerHTML = '';
            console.log(campingdata);
            
            
            	for(let i = 0;i<10;i++){
            			 const data = campingdata[i];
			             const address = data.placeaddress;
			             const iD = data.placeid;
			             const name = data.placename;
			             const url = data.placeurl;
			             const category = data.placecategory;
			             const region = data.placeregion;
			             const img = "images/"+region+"/thumbnail/"+name+".jpg";
			             console.log(img);
                   innerHTML += `
                   
                	<form name="detaildata" action="detailpage.jsp" method="get">
                        <div class="flex flex-row justify-start h-72 w-10/12 z-30 mr-10 px-5 py-5 rounded-lg bg-[#ffffff] border-2 border-black-100 mb-5 ">
                        	<div class="h-64 w-64 absolute rounded-lg relative">
                            	<img class="h-64 w-64 absolute rounded-lg" src="${"${img}"}" >
                            </div>
                      	<div class="w-full h-full relative flex flex-col mx-5 px-5">
                        <div class="" >
                        <input type = "hidden" name = "sdt" value="${"${result_date0}"}"></input>
                        <input type = "hidden" name = "edt" value="${"${result_date}"}"></input>
                            ${"${address}"}
                        </div>
                        <button type= "submit" name = "campname" value="${"${name}"}" onclick=location.href="detailpage.jsp" class="flex justify-start font-bold text-4xl">   
	              			 ${"${name}"}
	                    </button>    
                       		 ${"${category}"}
                       		<input type = "hidden" name = "region" value="${"${region}"}"></input>
		                    </div>
		                </div>
               		</form>
		                
                    `;
           }
             
               campdataListTag.innerHTML = innerHTML;
               getWeatherByRegion();
        }
        let weatherScoreArray = new Array();
        let sum = 0;
    	var weatherScoreArray_avg =0;
    	
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
             
             
				
        	 for(let i = result_date0; i < result_date0+result_date;i++){
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
	      		const avg = (sum/result_date);
	      		weatherScoreArray_avg = Math.round(avg * 100) / 100;
	      		
	      		var regionWeatherScore = {
	      			score : weatherScoreArray_avg, 
	      			region : regiondt
	      		};
	      		
	      		console.log(regionWeatherScore);
	      		//ajax전달
	      		/*$.ajax({
	      		　　type:'post'
	      		　　, contentType:'application/json'
	      		　　, data: JSON.stringify(regionWeatherScore)
	      		　　, url:'/post/weatherscore'
	      		　　, success: function(data) {
	      		　　　　console.log(data);
	      		　　}, error:function(e) {
	      		　　　　console.log("error: " + e);
	      		　　}
	      		});*/	
	      		
          	
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
                	case 'WB09':
                 		return 'images/rain.png';
                 		break;
                     default :
                         return 'images/raindrop.png';
              	}
                 
             }
    		
        	 
        	
	      	}
      	getWeatherByRegion();
      	campdata();
        </script>
    </body>
</html>    
