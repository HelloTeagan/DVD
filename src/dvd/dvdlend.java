package dvd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class dvdlend extends Basedome{
	static Scanner scan = new Scanner(System.in);
	 //查看DVD
	 public static void show_dvd() throws Exception{	
        String sql="select * from dvd";
        Object[] args= {};
        //ResultSet类，用来存放获取的结果集！
         executeQurry(sql,args);
         System.out.println("--->查看DVD排行榜");  
         System.out.println("---------------------------------------------");  
         System.out.println("编号  " + "\t状态"+"\t名称"+"\t\t借出日期"+"\t\t借出次数");  
         System.out.println("---------------------------------------------");  
         int id=0;
         String status=null;
         String name=null;
         String date=null;
         int num=0;
         while(rs.next()){
            //获取‘编号’这列数据
             id=rs.getInt("id");
             //获取剩余件数
             num=rs.getInt("num");              
             //获取‘状态’这列数据              
            int status1=rs.getInt("status");              
             if(status1==0) {
           	  status = "可借";
             }
             else {
           	  status = "已借出";
             }
            //获取‘名称’这列数据
            name=rs.getString("name");
            //获取‘日期’这列数据
            date=rs.getString("date");
            //输出结果
            System.out.printf("%d\t %s\t %s\t %s\t %d\n",id,status,name,date,num);          
         }
         closeResource();	        
		}	 
	 
	 //新增DVD
	 public static void add_dvd() throws Exception{
		System.out.println("--->请输入新增DVD名称"); 
    	String name = scan.next(); 
    	name="《"+name+"》";
    	String sql="INSERT INTO dvd (name) VALUES (?)";
        Object[] args= {name};
        executeUpdata(sql,args);
        if(count>0) {
        	System.out.println("添加成功");
        }
        else {
        	System.out.println("添加失败");
        }              
        closeResource();	
		}
	 //删除DVD
	 public static void deletedvd() throws Exception{
    	System.out.println("--->请输入删除的DVD名称");
    	String name = scan.next();
    	name="《"+name+"》";
    	String sql="DELETE  from dvd WHERE name like ?";
        Object[] args= {name};
        executeUpdata(sql, args);
        if(count>0) {
        	System.out.println("删除成功");
        }
        else {
        	System.out.println("未查询到相关信息");
        }              
          closeResource();	
		}
	 //借出dvd
	 public static void lend_dvd() throws Exception{	
        //ResultSet类，用来存放获取的结果集！
         System.out.println("--->请输入借出DVD名称");  
         String name = scan.next();
         name="《"+name+"》";
         String sql="select * from dvd where name like ?";
         Object[] args= {name};          
         executeQurry(sql,args);
         int num=0;
         int status;
         while(rs.next()){
             //获取借出次数
             num=rs.getInt("num"); 
             status=rs.getInt("status"); 
             if(status==0) {
           	  //获取当前日期
           	  Date now = new Date(); 
      		      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
      		      String hehe = dateFormat.format( now ); 
           	  System.out.println(hehe);
           	  num++;
           	  String sql1="UPDATE dvd SET status=1 ,num = ?,date = ? WHERE name like ?";
                 Object[] args1= {num,hehe,name}; 
                 executeUpdata(sql1, args1);
                 if(count>0) {
               	  System.out.println("借出成功");
                 }
                 else {
                 	  System.out.println("未查询到相关信息");
                 }          
             }
             else {
           	  System.out.println("已借出");
             }
         }
         closeResource();	        
		}	 
	 //归还dvd
	 public static void back_dvd() throws Exception{	
        //ResultSet类，用来存放获取的结果集！
         System.out.println("--->请输入-->归还DVD名称");  
         String name = scan.next();
         name="《"+name+"》";
         String sql="select * from dvd where name like ?";
         Object[] args= {name};          
         executeQurry(sql,args);
         int status;
         while(rs.next()){
       	  status=rs.getInt("status"); 
       	  if(status==1) {        		  
       		  String sql1="UPDATE dvd SET status = 0 WHERE name like ?";
       		  Object[] args1= {name}; 
       		  executeUpdata(sql1, args1);
       		  if(count>0) {
       			  System.out.println("归还成功");
       		  }
       		  else {
       			  System.out.println("未查询到相关信息");
       		  }          
       	  }                
         }
         closeResource();	        
		}	 
	 public static void main(String[] args){
		   System.out.println("***********欢迎来到kk_DVD闪租********************** ");		   
		   System.out.println("---->请选择业务 <----\n1)增加DVD\n2)查看DVD\n3)删除DVD\n4)借出DVD\n5)归还DVD\n6)退出系统\n");
		   do {
			   int j = scan.nextInt();
			   switch (j) {
			   case 0:
				   System.out.println("---->请选择业务 <------\n1)增加DVD\n2)查看DVD\n3)删除DVD\n4)借出DVD\n5)归还DVD\n6)退出系统\n");
				   break;
			   case 1:  
				   try{
					   add_dvd();					  		   
				    }catch (Exception e){
				    	e.printStackTrace();
				    };break;
			   case 2: 
				   try{
					   show_dvd();					  		   
				    }catch (Exception e){
				    	e.printStackTrace();
				    };break;			   
			   case 3: 
				   try{
					   deletedvd();					  		   
				    }catch (Exception e){
				    	e.printStackTrace();
				    };break;  
			   case 4: 
				   try{
					   lend_dvd();					  		   
				    }catch (Exception e){
				    	e.printStackTrace();
				    };break;			   
			   case 5: 
				   try{
					   back_dvd();					  		   
				    }catch (Exception e){
				    	e.printStackTrace();
				    };break;
			   case 6: 
				  System.out.println("***********kk_DVD闪租系统谢谢使用******************* ");
				  return ;
		       }
			   System.out.println("***输入0返回**** ");	
		   }while(true); 		  
	 }

}
