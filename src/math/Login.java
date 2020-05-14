package math;

import java.awt.*;
import javax.swing.JWindow;
import java.applet.*;
import java.lang.Object;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JRootPane;

public class Login extends JFrame{
	private JFrame fr=null;
	
	private Button next=null;
	private JButton close=null;
	private Button nandu=null;
	private Label  formula;

	private Checkbox a_0;
	private JLabel jl1;
	private JLabel jl2;
	private JButton b_1;
	private Checkbox a_1;
	private JTextField jtf1;
	private JPasswordField jpf1;
	private Checkbox a_2;
	private Checkbox a_3;
	private CheckboxGroup zu;

	private Label b_ans0;
	private Label b_ans1;
	private Label b_ans2;
	private Label b_ans3;


	private CreateFormula cf;

	private Checkbox c_0;
	private Checkbox c_1;
	private Checkbox c_2;


	private Label d_ans0;
	private Label d_ans1;
	private Label d_ans2;

	private Label tongji0;
	private Label tongji1;
	private Label tongji2;

	int all=0,r=0,xx=0;


 


	Login()
	{
		init();
		setBounds();
		addComps();
		addListeners();
		refresh();
		this.getContentPane().add(new JLabel("Just a test."));
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);// 采用指定的窗口装饰风格
		this.setSize(300, 150);
	}

	void init()//初始化
	{
		fr=new JFrame("数学题库-By彭宇帆&王禧龙");//标题


		next=new Button("下一题");
		nandu=new Button("确定");
		
		formula=new Label("formula------");
		b_ans0=new Label("formula0");
		b_ans1=new Label("formula1");
		b_ans2=new Label("formula2");
		b_ans3=new Label("formula3");
		d_ans0=new Label("简单");
		d_ans1=new Label("中等");
		d_ans2=new Label("困难");
		tongji0=new Label("     共做:  "+all+"题");
		tongji1=new Label("     做对:  "+r+"题");
		tongji2=new Label("     做错:  "+(all-r)+"题");
		jtf1 = new JTextField(10);

        jpf1 = new JPasswordField(10);// 设置布局管理(上面忘记：extends JFrame，这里出错了)
        this.setLayout(new GridLayout(3, 1));

		zu=new CheckboxGroup();
		a_0=new Checkbox(null,false,zu);
		a_1=new Checkbox(null,false,zu);
		a_2=new Checkbox(null,false,zu);
		a_3=new Checkbox(null,false,zu);
		c_0=new Checkbox(null,false,zu);
		c_1=new Checkbox(null,false,zu);
		c_2=new Checkbox(null,false,zu);
		cf=new CreateFormula();
		jl1 = new JLabel(new ImageIcon(getClass().getResource("/math/1.png")));
		jl2 = new JLabel(new ImageIcon(getClass().getResource("/math/circle.gif")));
		b_1=new JButton(new ImageIcon(getClass().getResource("/math/2.png")));
		close=new JButton(new ImageIcon(getClass().getResource("/math/c.png")));
	}
	void addComps()//添加组件，将标签,按钮添加到frame中
	{

		fr.add(next);
		fr.add(b_1);
		fr.add(close);
		fr.add(jtf1);
		fr.add(jpf1);
		fr.add(nandu);
		fr.add(formula);
		fr.add(a_0);
		fr.add(a_1);
		fr.add(a_2);
		fr.add(a_3);
		fr.add(b_ans0);
		fr.add(b_ans1);
		fr.add(b_ans2);
		fr.add(b_ans3);
		fr.add(c_0);
		fr.add(c_1);
		fr.add(jl2);
		fr.add(jl1);
		fr.add(c_2);
		fr.add(d_ans0);
		fr.add(d_ans1);
		fr.add(d_ans2);
		fr.add(tongji0);
		fr.add(tongji1);
		fr.add(tongji2);
		LoginListener ll = new LoginListener(fr,jtf1,jpf1);
		b_1.addActionListener(ll);


	}
	
	
	void setBounds()//
	{
		fr.setBounds(448,200,1024,768);
		
		close.setBounds(980,6,30,30);
		close.setBorderPainted(false);
		close.setContentAreaFilled(false);
		fr.setUndecorated(true);
		b_1.setBounds(408,680,224,58);
		b_1.setBorderPainted(false);
		b_1.setContentAreaFilled(false);
		jl2.setBounds(0,-72,1024,768);
		jl1.setBounds(0,0,1024,768);
		
		jtf1.setBounds(240,618,224,35);
		jpf1.setBounds(565,618,224,35);
		fr.setLayout(null);
		fr.setVisible(true);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


	}
	
	 
	void setForeground()
	{
		next.setForeground(Color.blue);
	}
	void setBackground()
	{
		next.setBackground(Color.blue);
	}        
	void addListeners()//添加事件监听器
	{
		close.addActionListener(new ActionListener()//下一题

				{
					public void actionPerformed(ActionEvent e) {
						fr.dispose();
					}
					});
		fr.addWindowListener(new WindowAdapter()
		{public void windowClosing(WindowEvent e){
			int c=st();
			if(c==JOptionPane.YES_OPTION)
				System.exit(0);//退出
		}
		});


		nandu.addActionListener(new ActionListener()//调整难度
		{      public void actionPerformed(ActionEvent e){

			if(c_0.getState()) xx=0;
			if(c_1.getState()) xx=1;
			if(c_2.getState()) xx=2;
			cf.tiaozhennandu(xx);
			refresh();
		}
		});


		next.addActionListener(new ActionListener()//下一题

		{
			public void actionPerformed(ActionEvent e) {
				String rightMessage="恭喜你!答对啦!";
				String wrongMessage="答错喽~正确答案应该是"+cf.getResult();
				//判断是否答题
				int c=-1;
				if(a_0.getState()) c=0;
				if(a_1.getState()) c=1;
				if(a_2.getState()) c=2;
				if(a_3.getState()) c=3;
				if(c==-1)
				{alert("请选择","您还没有选择");
				}
				else
				{if(c==cf.getRight())
				{
					alert("正确",rightMessage);
					r++;
				}
				else alert("错误",wrongMessage);
					all++;
					refresh();
					tongji0.setText("     共做:  "+all+"题");
					tongji1.setText("     做对:  "+r+"题");
					tongji2.setText("     做错:  "+(all-r)+"题");
				}
			}
		});
	}


	void refresh()
	{
		cf.run();//调用run方法给出题目和答案
		formula.setText((all+1)+".   "+cf.getFormula());
		b_ans0.setText("A     "+cf.getAnswers(0));
		b_ans1.setText("B     "+cf.getAnswers(1));
		b_ans2.setText("C     "+cf.getAnswers(2));
		b_ans3.setText("D     "+cf.getAnswers(3));
		zu.setSelectedCheckbox(null);
		a_0.setState(false);
		a_1.setState(false);
		a_2.setState(false);
		a_3.setState(false);
		if(xx==0) {d_ans0.setForeground(Color.red);d_ans1.setForeground(Color.black);d_ans2.setForeground(Color.black);}
		if(xx==1) {d_ans0.setForeground(Color.black);d_ans1.setForeground(Color.red);d_ans2.setForeground(Color.black);}
		if(xx==2) {d_ans0.setForeground(Color.black);d_ans1.setForeground(Color.black);d_ans2.setForeground(Color.red);}

	}

	void alert(String title,String message)//设计窗口格式
	{
		JOptionPane.showMessageDialog(
				fr,message,title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	int st()
	{
		double s;
		String hint;
		if(all>0)//根据相应得分给出相应评价并统计信息
		{
			s=(double)r/all*100;
			s=(double)((int)(s*10+0.5))/10; //保留一位小数
		}
		else
			s=-1;
		if(s==-1)
			hint="快点做题啦~";
		else if(s<60)
			hint="你要努力哦~";
		else if(s<80)
			hint="还可以哦~";
		else if(s<90)
			hint="真的很不错哦~";
		else hint="太棒啦~";
		String str="统计信息:\n您总共做题:  "+all+"\n共计正确数:  "+r+"\n共计错误数:  "+(all-r)+"\n正确率:  "+
				(all==0?"-":s+"%")+"\n"+hint+"\n\n您真的要退出吗?";
		return JOptionPane.showConfirmDialog(fr,str,"统计信息",JOptionPane.YES_NO_OPTION);
	}





	public static void main(String[] args)  
	{   
	new Login();//调用Login方法
	}  
}

 


