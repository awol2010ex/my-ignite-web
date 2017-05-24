var axios =require("axios");


var serviceApi={
       invokeApi(serviceName,methodName,data){

           var params = new URLSearchParams();
           if(data){
               params.append('data',JSON.stringify(data));
           }
	       return axios.post('./services/'+serviceName+"/"+methodName,params)
           .then(function (data) {
               return data.data;
           })
      .    catch(function (err) { console.warn('Error in invokeApi', err) })
       }
};

export {
	serviceApi
}