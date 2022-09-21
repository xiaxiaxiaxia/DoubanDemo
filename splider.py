from  bs4  import BeautifulSoup #网页解析，获取数据
import  re  #正则表达式，进行文字匹配
import urllib.request,urllib.error #指定URL，获取网页数据
import sqlite3 #进行SQLite数据库操作

def main():
   baseurl="https://movie.douban.com/top250?start="
   # init_db()
   #1.爬取网页
   datalist=getData(baseurl)
   #savepath = ".\\豆瓣电影top250.xls"
   #3.保存数据
   #saveData(datalist,savepath)
   dbpath='G://movie250.db'
   saveData2DB(datalist,dbpath)

findLink=re.compile(r'<a href="(.*?)">') #正则表达式模式的匹配，影片详情
findImgSrc=re.compile(r'<img.*src="(.*?)"',re.S)#re.S让换行符包含在字符中,图片信息
findTitle=re.compile(r'<span class="title">(.*)</span>')#影片片名
findRating=re.compile(r'<span class="rating_num" property="v:average">(.*)</span>')#找到评分
findJudge=re.compile(r'<span>(\d*)人评价</span>')#找到评价人数 #\d表示数字
findInq=re.compile(r'<span class="inq">(.*)</span>')#找到概况
findBd=re.compile(r'<p class="">(.*?)</p>',re.S) #找到影片的相关内容，如导演，演员等

# 爬取网页
def getData(baseurl):
    datalist=[]
    for i in range(0,10):#左闭右开
        url=baseurl+str(i*25) #字符串的拼接，调用获取页面信息的函数，10次（一共10页)
        html=askURL(url)
    #2.逐一解析数据,一定是在for循环里面的，因为是获取一次网页，就应该解析一次。
        soup=BeautifulSoup(html,"html.parser")
        for item in soup.find_all('div',class_="item"): #查找符合要求的字符串，形成列表
        #div同时满足class=item,如果没有下划线，则会报错。
            data=[] #保存一部电影的所有信息
            item=str(item) #把item转换为字符串
            link=re.findall(findLink,item)[0]
            data.append(link)

            imgSrc=re.findall(findImgSrc,item)[0]
            data.append(imgSrc)

            titles=re.findall(findTitle,item) #片名可能只有一个中文名，没有外文名
            if(len(titles)==2):
                ctitle=titles[0]
                data.append(ctitle)
                otitle=titles[1].replace("/","")#去掉无关的符号
                data.append(otitle)
            else: #片名只有一个中文名字时
                data.append(titles[0])
                data.append(' ')
            rating=re.findall(findRating,item)[0] #添加评分
            data.append(rating)

            judgeNum=re.findall(findJudge,item)[0] #添加评价人数
            data.append(judgeNum)

            inq=re.findall(findInq,item)  #添加概述
            if len(inq)!=0:
                inq=inq[0].replace("。","")
                data.append(inq)
            else:
                data.append(" ") #留空
            bd=re.findall(findBd,item)[0]
            bd=re.sub('<br(\s+)?/>(\s+)?'," ",bd)
            bd=re.sub('/'," ",bd)
            data.append(bd.strip())#去掉前后的空格
            datalist.append(data)  #把处理好的一部电影信息放入datalist
    print(datalist)
    return datalist


def askURL(url): #写函数是为了代码的复用，因为top250一共有5页
    head={ #模拟浏览器头部信息，向豆瓣服务器发送消息
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36"
    } #用户代理，表示告诉豆瓣服务器，我们是什么类型的机器。本质上是告诉浏览器，我们可以接收什么水平的文件内容
    request=urllib.request.Request(url,headers=head)
    html=""
    try:
        response=urllib.request.urlopen(request)
        html=response.read().decode('utf-8') #解析
        #print(html)
    except urllib.error.URLError as e:
        if hasattr(e,"code"): #如果有这个属性的话
            print(e.code)
        if hasattr(e,"reason"):
            print(e.reason)
    return html

# 将爬取的数据保存在sqlite数据库中
def  saveData2DB(datalist,dbpath):
    init_db(dbpath)
    #print(".........")
    conn=sqlite3.connect(dbpath)
    cur=conn.cursor()
    for data in datalist:
        for index in range(len(data)):

            if index == 4 or index == 5:
                continue  #Python continue 语句跳出本次循环，而break跳出整个循环
                #continue 语句用来告诉Python跳过当前循环的剩余语句，然后继续进行下一轮循环。
                #break语句用来终止循环语句，即循环条件没有False条件或者序列还没被完全递归完，也会停止执行循环语句。
            data[index]='"'+data[index]+'"'  #把合适的字段可以放成字符串内容，可以用一个判断实现

        sql='''
                insert into movie250 (
                info_link,pic_link,cname,ename,score,rated,instroduction,info)
                values(%s) '''%",".join(data)

        #每拼好一个data语句，就执行一次sql语句
        print(sql)
        cur.execute(sql)
        conn.commit()
    cur.close()
    conn.close()



def init_db(dbpath):
    sql='''
        create table movie250
        (
        id integer primary key autoincrement,
        info_link text,
        pic_link text,
        cname text,
        ename text,
        score text,
        rated text,
        instroduction text,
        info text
        )
    ''' #创建数据表
    conn=sqlite3.connect(dbpath)
    cursor=conn.cursor()
    cursor.execute(sql)
    conn.commit()
    conn.close()

if __name__ == '__main__':
    main()
    print('爬取成功~')