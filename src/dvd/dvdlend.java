package dvd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class dvdlend extends Basedome{
	static Scanner scan = new Scanner(System.in);
	 //�鿴DVD
	 public static void show_dvd() throws Exception{	
        String sql="select * from dvd";
        Object[] args= {};
        //ResultSet�࣬������Ż�ȡ�Ľ������
         executeQurry(sql,args);
         System.out.println("--->�鿴DVD���а�");  
         System.out.println("---------------------------------------------");  
         System.out.println("���  " + "\t״̬"+"\t����"+"\t\t�������"+"\t\t�������");  
         System.out.println("---------------------------------------------");  
         int id=0;
         String status=null;
         String name=null;
         String date=null;
         int num=0;
         while(rs.next()){
            //��ȡ����š���������
             id=rs.getInt("id");
             //��ȡʣ�����
             num=rs.getInt("num");              
             //��ȡ��״̬����������              
            int status1=rs.getInt("status");              
             if(status1==0) {
           	  status = "�ɽ�";
             }
             else {
           	  status = "�ѽ��";
             }
            //��ȡ�����ơ���������
            name=rs.getString("name");
            //��ȡ�����ڡ���������
            date=rs.getString("date");
            //������
            System.out.printf("%d\t %s\t %s\t %s\t %d\n",id,status,name,date,num);          
         }
         closeResource();	        
		}	 
	 
	 //����DVD
	 public static void add_dvd() throws Exception{
		System.out.println("--->����������DVD����"); 
    	String name = scan.next(); 
    	name="��"+name+"��";
    	String sql="INSERT INTO dvd (name) VALUES (?)";
        Object[] args= {name};
        executeUpdata(sql,args);
        if(count>0) {
        	System.out.println("��ӳɹ�");
        }
        else {
        	System.out.println("���ʧ��");
        }              
        closeResource();	
		}
	 //ɾ��DVD
	 public static void deletedvd() throws Exception{
    	System.out.println("--->������ɾ����DVD����");
    	String name = scan.next();
    	name="��"+name+"��";
    	String sql="DELETE  from dvd WHERE name like ?";
        Object[] args= {name};
        executeUpdata(sql, args);
        if(count>0) {
        	System.out.println("ɾ���ɹ�");
        }
        else {
        	System.out.println("δ��ѯ�������Ϣ");
        }              
          closeResource();	
		}
	 //���dvd
	 public static void lend_dvd() throws Exception{	
        //ResultSet�࣬������Ż�ȡ�Ľ������
         System.out.println("--->��������DVD����");  
         String name = scan.next();
         name="��"+name+"��";
         String sql="select * from dvd where name like ?";
         Object[] args= {name};          
         executeQurry(sql,args);
         int num=0;
         int status;
         while(rs.next()){
             //��ȡ�������
             num=rs.getInt("num"); 
             status=rs.getInt("status"); 
             if(status==0) {
           	  //��ȡ��ǰ����
           	  Date now = new Date(); 
      		      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//���Է�����޸����ڸ�ʽ
      		      String hehe = dateFormat.format( now ); 
           	  System.out.println(hehe);
           	  num++;
           	  String sql1="UPDATE dvd SET status=1 ,num = ?,date = ? WHERE name like ?";
                 Object[] args1= {num,hehe,name}; 
                 executeUpdata(sql1, args1);
                 if(count>0) {
               	  System.out.println("����ɹ�");
                 }
                 else {
                 	  System.out.println("δ��ѯ�������Ϣ");
                 }          
             }
             else {
           	  System.out.println("�ѽ��");
             }
         }
         closeResource();	        
		}	 
	 //�黹dvd
	 public static void back_dvd() throws Exception{	
        //ResultSet�࣬������Ż�ȡ�Ľ������
         System.out.println("--->������-->�黹DVD����");  
         String name = scan.next();
         name="��"+name+"��";
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
       			  System.out.println("�黹�ɹ�");
       		  }
       		  else {
       			  System.out.println("δ��ѯ�������Ϣ");
       		  }          
       	  }                
         }
         closeResource();	        
		}	 
	 public static void main(String[] args){
		   System.out.println("***********��ӭ����kk_DVD����********************** ");		   
		   System.out.println("---->��ѡ��ҵ�� <----\n1)����DVD\n2)�鿴DVD\n3)ɾ��DVD\n4)���DVD\n5)�黹DVD\n6)�˳�ϵͳ\n");
		   do {
			   int j = scan.nextInt();
			   switch (j) {
			   case 0:
				   System.out.println("---->��ѡ��ҵ�� <------\n1)����DVD\n2)�鿴DVD\n3)ɾ��DVD\n4)���DVD\n5)�黹DVD\n6)�˳�ϵͳ\n");
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
				  System.out.println("***********kk_DVD����ϵͳллʹ��******************* ");
				  return ;
		       }
			   System.out.println("***����0����**** ");	
		   }while(true); 		  
	 }

}
