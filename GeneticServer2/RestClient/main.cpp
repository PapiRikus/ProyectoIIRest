//#include <iostream>
//#include <fstream>
//#include <jsoncpp/json/json.h>
//
//using namespace std;
//
//int main() {
//    ifstream ifs("profile.json");
//    Json::Reader reader;
//    Json::Value obj;
//    reader.parse(ifs, obj);     // Reader can also read strings
//    cout << "Last name: " << obj["lastname"].asString() << endl;
//    cout << "First name: " << obj["firstname"].asString() << endl;
//    return 1;
//}

//#include <jsoncpp/json/json.h>
//#include <jsoncpp/json/reader.h>
//#include <jsoncpp/json/writer.h>
//#include <jsoncpp/json/value.h>
//#include <string>
//#include <iostream>
//
//int main( int argc, const char* argv[] )
//{
//
//    std::string strJson = "{\"mykey\" : \"myvalue\"}"; // need escape the quotes
//
//    Json::Value root;   
//    Json::Reader reader;
//    bool parsingSuccessful = reader.parse( strJson.c_str(), root );     //parse process
//    if ( !parsingSuccessful )
//    {
//        std::cout  << "Failed to parse"<< reader.getFormattedErrorMessages();
//        return 0;
//    }
//    std::cout << root.get("mykey", "A Default Value if not exists" ).asString()<< std::endl;
//    return 0;
//}

//////////////////////////////////////////
#include <iostream>
#include <string>
#include <curl/curl.h>
#include <jsoncpp/json/json.h>
#include <jsoncpp/json/reader.h>
#include <jsoncpp/json/writer.h>
#include <jsoncpp/json/value.h>


static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void *userp)
{
    ((std::string*)userp)->append((char*)contents, size * nmemb);
    return size * nmemb;
}

int main(void)
{
  CURL *curl;
  CURLcode res;
  std::string readBuffer;

  curl = curl_easy_init();
  if(curl) {
    curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/GeneticServer/webapi/Gladiator/a");
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &readBuffer);
    res = curl_easy_perform(curl);
    curl_easy_cleanup(curl);
    std::cout<< res;
    std::cout << readBuffer << std::endl;
    
    
        std::string strJson = "{\"mykey\" : \"myvalue\"}"; // need escape the quotes

    Json::Value root;   
    Json::Reader reader;
    bool parsingSuccessful = reader.parse( readBuffer.c_str(), root );     //parse process
    if ( !parsingSuccessful )
    {
        std::cout  << "Failed to parse"<< reader.getFormattedErrorMessages();
        return 0;
    }
    std::cout << root.get("genSize", "A Default Value if not exists" ).asString()<< std::endl;
  }
  return 0;
}
//////////////////////////////////




//
//
//#include <stdlib.h>
//#include<cstdlib>
//
//#include <iostream>
//#include <memory>
//#include <string>
//
//#include <curl/curl.h>
//#include <json/json.h>
//
//namespace
//{
//    std::size_t callback(
//            const char* in,
//            std::size_t size,
//            std::size_t num,
//            std::string* out)
//    {
//        const std::size_t totalBytes(size * num);
//        out->append(in, totalBytes);
//        return totalBytes;
//    }
//}
//
//int main()
//{
//    const std::string url("http://localhost:8080/GeneticServer/webapi/Gladiator/a");
//
//    CURL* curl = curl_easy_init();
//
//    // Set remote URL.
//    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
//
//    // Don't bother trying IPv6, which would increase DNS resolution time.
//    curl_easy_setopt(curl, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
//
//    // Don't wait forever, time out after 10 seconds.
//    curl_easy_setopt(curl, CURLOPT_TIMEOUT, 10);
//
//    // Follow HTTP redirects if necessary.
//    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
//
//    // Response information.
//    int httpCode(0);
//    std::unique_ptr<std::string> httpData(new std::string());
//
//    // Hook up data handling function.
//    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, callback);
//
//    // Hook up data container (will be passed as the last parameter to the
//    // callback handling function).  Can be any pointer type, since it will
//    // internally be passed as a void pointer.
//    curl_easy_setopt(curl, CURLOPT_WRITEDATA, httpData.get());
//
//    // Run our HTTP GET command, capture the HTTP response code, and clean up.
//    curl_easy_perform(curl);
//    curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &httpCode);
//    curl_easy_cleanup(curl);
//       
//    if (httpCode == 200)
//    {
//        std::cout << "\nGot successful response from " << url << std::endl;
////        
//        Json::Value jsonData;
//        Json::Reader jsonReader;
//            
//              
//    }
////        std::cout<<"pasa por aqui";
////        std::cout << "\nGot successful response from " << url << std::endl;
////        std::cout<<"pasap 1";
////        // Response looks good - done using Curl now.  Try to parse the results
////        // and print them out.
////        Json::Value jsonData;
////        Json::Reader jsonReader;
////
////        if (jsonReader.parse(*httpData, jsonData))
////        {
////            std::cout << "Successfully parsed JSON data" << std::endl;
////            std::cout << "\nJSON data received:" << std::endl;
////            std::cout << jsonData.toStyledString() << std::endl;
////
////            const std::string dateString(jsonData["date"].asString());
////            const std::size_t unixTimeMs(
////                    jsonData["milliseconds_since_epoch"].asUInt64());
////            const std::string timeString(jsonData["time"].asString());
////
////            std::cout << "Natively parsed:" << std::endl;
////            std::cout << "\tDate string: " << dateString << std::endl;
////            std::cout << "\tUnix timeMs: " << unixTimeMs << std::endl;
////            std::cout << "\tTime string: " << timeString << std::endl;
////            std::cout << std::endl;
////        }
////        else
////        {
////            std::cout << "Could not parse HTTP data as JSON" << std::endl;
////    //        std::cout << "HTTP data was:\n" << *httpData.get() << std::endl;
////            return 1;
////        }
////    }
////    else
////    {
////        std::cout << "Couldn't GET from " << url << " - exiting" << std::endl;
////        return 1;
////    }
////
////    return 0;
////}
////






/*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///* 
// * File:   main.cpp
// * Author: cp
// *
// * Created on 28 de abril de 2017, 12:58 AM
// */
//
//#include <cstdlib>
//#include <stdio.h>
//#include <curl/curl.h>
// 
//int main(void)
//{
//  CURL *curl;
//  CURLcode res;
// 
//  curl_global_init(CURL_GLOBAL_DEFAULT);
// 
//  curl = curl_easy_init();
//  if(curl) {
//    curl_easy_setopt(curl, CURLOPT_URL, "http://localhost:8080/GeneticServer/webapi/Gladiator");
//    /* Perform the request, res will get the return code */ 
//    res = curl_easy_perform(curl);
//    /* Check for errors */ 
//    if(res != CURLE_OK)
//      fprintf(stderr, "curl_easy_perform() failed: %s\n",
//              curl_easy_strerror(res));
// 
//    /* always cleanup */ 
//    curl_easy_cleanup(curl);
//  }
// 
//  curl_global_cleanup();
// 
//  return 0;
//}