import os
import sys
import json
import datetime

environment=os.getcwd()
# def main():
#     pass

# if __name__ == '__main__':
#     main()
# today = datetime.datetime.now().date()

try:
    file_path = os.path.expanduser("~/.jafr/user-settings.json")
   
    with open(file_path, "r") as file:
        data = file.read()
        obj = json.loads(data)
        # print(obj["master"])
        directory = obj["master"]

    os.chdir(directory)
    username=os.environ['USER']
except:
    sys.stderr.write("Jafr's chosen master directory does not exist.\n")
    sys.exit(1)
# print(os.environ['USER'])
#print(directory)
tasks_dict = directory+"/tasks.md"
meetings_dict = directory+"/meetings.md"


if os.path.isabs(sys.argv[1]):
    passwd_dict = f"{sys.argv[1]}"
else:
    passwd_dict = environment+'/'+sys.argv[1]


today1 = datetime.datetime(2024, 8, 2)
today1 = today1.date()
today2 = datetime.datetime(2024, 8, 2)
# time_now =today.strftime("%d/%m/%y")
# today1=datetime.datetime.now().date()
# today2=datetime.datetime.now()

cont1 = 0
cont2 = 1
cont3 = 0
cont4=0
cont5=0


flag1 = 1
# flag2=1
# flag3=1
# flag4=1
####
flag5 = 1
flag6 = 1
flag7 = 1
flag8 = 1
flag9 = 1
flag10 =1
flag11= 1
flag12=1
flag13=1
flag14=1
flag15=1
flag16=1
flag17=1
flag18=1

try:
    with open(tasks_dict, "r") as file:
        pass
    with open(meetings_dict, "r") as file:
        pass
except:
    sys.stderr.write("Missing tasks.md or meetings.md file.\n")
    sys.exit(1)
    


with open(tasks_dict, "r") as file:
    print(f"Just a friendly reminder! You have these tasks to finish today.")
    for line in file:
        if "-" in line:
            if "not complete" in line:
                content1 = line.strip()
                atime1 = time1 = content1.find("Due: ")
                time1 = content1[time1+5:].strip()
                time1 = time1[:9].strip()
                try:
                    time1 = datetime.datetime.strptime(time1, "%d/%m/%y").date()
                    # convert time to string to display
                    btime1 = time1.strftime("%d/%m/%y")
                    time1 = time1-today1
                    time1 = int(time1.days)
                    if time1 < 1:

                        print(content1[:atime1].strip())
                except:
                    pass
                
print("")

with open(tasks_dict, "r") as file:
    print(f"These tasks need to be finished in the next three days!")
    for line in file:
        if "-" in line:
            if "not complete" in line:
                content1 = line.strip()
                atime1 = time1 = content1.find("Due: ")
                time1 = content1[time1+5:].strip()
                time1 = time1[:9].strip()
                try:
                    time1 = datetime.datetime.strptime(time1, "%d/%m/%y").date()
                    btime1 = time1.strftime("%d/%m/%y")
                    time1 = time1-today1
                    time1 = int(time1.days)
                    if 1 <= time1 <= 3:
                        print(f"{content1[:atime1].strip()} by {btime1}")
                except:
                    pass
               
print("")


#             #print(line.strip(" "), end='')

with open(meetings_dict, "r") as file:
    print("You have the following meetings today!")
    for line in file:
        if "-" in line:
            content2 = line.strip()
            atime2 = time2 = content2.find("Scheduled: ")
            time2 = content2[time2+11:].strip()
            try:

                time2 = datetime.datetime.strptime(time2, "%H:%M %d/%m/%y")
                time2_days=time2.date()
                
                btime2 = time2.strftime("%H:%M")
                ctime2 = time2.strftime("%d/%m/%y")
                time2 = (time2_days-today1)
                time2 = int(time2.days)
                if time2 < 1:
                    print(f"{content2[:atime2]}at {btime2}")
            except:
                pass
print("")

with open(meetings_dict, "r") as file:
    print("You have the following meetings scheduled over the next week!")
    for line in file:
        if "-" in line:
            content2 = line.strip()
            atime2 = time2 = content2.find("Scheduled: ")
            time2 = content2[time2+11:].strip()
            try:

                time2 = datetime.datetime.strptime(time2, "%H:%M %d/%m/%y")
                time2_days=time2.date()
                btime2 = time2.strftime("%H:%M")
                ctime2 = time2.strftime("%d/%m/%y")
                time2 = (time2_days-today1)
                time2 = int(time2.days)
                if 1 <= time2 <= 7:
                    print(f"{content2[:atime2]}on {ctime2} at {btime2}")
            except:
                pass
            
print("")
while flag1:
    cont1 = 0
    cont2 = 1
    cont3 = 0
    cont4=0
    cont5=0


    flag1 = 1
    # flag2=1
    # flag3=1
    # flag4=1
    ####
    flag5 = 1
    flag6 = 1
    flag7 = 1
    flag8 = 1
    flag9 = 1
    flag10 =1
    flag11= 1
    flag12=1
    flag13=1
    flag14=1
    flag15=1
    flag16=1
    flag17=1
    flag18=1
    print('''What would you like to do?
1. Complete tasks
2. Add a new meeting.
3. Share a task.
4. Share a meeting.
5. Change Jafr's master directory.
6. Exit''')

# part0 done
    while flag16:
        try:
            user_input = int(input())
            if 1<=user_input<=6:
                flag16=0
        except:
            print("invalid menu num,try again")

    if user_input == 1:
        with open(tasks_dict, "r") as file:
            content=file.read()
            if not content:
                    print("No tasks to complete!")
                    flag8=0
        with open(tasks_dict, "r") as file:   #why this is working..?
            for line in file:
                
                if "-" in line:
                    if "not complete" in line:
                        
                        if flag5 == 1:
                            print("Which task(s) would you like to mark as completed?")
                            flag5 = 0
                        try:
                            content1 = line.strip()
                            atime1 = time1 = content1.find("Due: ")
                            time1 = content1[time1+5:].strip()
                            time1 = time1[:9].strip()
                            time1 = datetime.datetime.strptime(time1, "%d/%m/%y").date()
                            # convert time to string to display
                            btime1 = time1.strftime("%d/%m/%y")
                            print(f"{cont2}. {content1[2:atime1].strip()} by {btime1}")
                            cont2 += 1
                        except:
                            pass
                        
            
            if flag8 == 1:
                while flag11:
                    try:
                        a = input()
                        b = [int(string) for string in a.split()]
                        a=sorted(b)
                        lenb=len(b)
                        lena=len(set(a))
                        if lena!=lenb or a[-1]>cont2-1:
                            print("invalid, try again")
                        else:
                            flag11=0
                    except:
                        print("invalid task num, try again")
                aseq=0
                
                with open(tasks_dict, "r") as file:
                    line = file.readlines()
                with open(tasks_dict, "w") as file:
                    for line in line:
                        if "-" in line:
                            if "not complete" in line:
                                try:
                                    content1 = line.strip()
                                    atime1 = time1 = content1.find("Due: ")
                                    time1 = content1[time1+5:].strip()
                                    time1 = time1[:9].strip()
                                    time1 = datetime.datetime.strptime(time1, "%d/%m/%y").date()
                                    # convert time to string to display
                                    btime1 = time1.strftime("%d/%m/%y")
                                    cont3 += 1
                                except:
                                    pass
                                try:
                                    if cont3 == a[aseq]:
                                        line2 = line.replace(
                                            "not complete", "complete")
                                        flag9 = 0
                                        aseq+=1
                                        file.write(line2)
                                    else:
                                        file.write(line)

                                except:
                                    file.write(line)
                            else:
                                file.write(line)
                        else:
                            file.write(line)
            if flag9 == 0:
                print("Marked as complete.")
    elif user_input == 2:
        print("Please enter a meeting description:") #"no Scheduled:"
        while flag12: 
            a = input()
            if "Scheduled:" in a or a.strip()=="":
                print("invalid,try again")
                
            else:
                flag12=0 

        print("Please enter a date:")
        while flag13:
            b = input()
            try:
                b = datetime.datetime.strptime(b, "%d/%m/%y").date()
                b = b.strftime("%d/%m/%y")
                flag13=0
            except:
                print("invalid,try again")

        print("Please enter a time:")
        while flag14:
            c = input()
            try:
                c = datetime.datetime.strptime(c, "%H:%M").time()
                c = c.strftime("%H:%M")
                flag14=0
            except:
                print("invalid,try again")

        with open(meetings_dict, 'a') as file:
            file.write(f"\n##### added by you")
            file.write(f"\n- {a} Scheduled: {c} {b}\n")
        print(f"Ok, I have added {a} on {b} at {c}.")
        content_add=(f"- {a} Scheduled: {c} {b}\n")
        user_input2=input("Would you like to share this meeting? [y/n]: ")
        if user_input2=='y':
            print("Who would you like to share with?")
            with open (passwd_dict,"r") as file:    #sharemeeting
                for line in file:
                    content3=line.split(":")
                        
                    content3_user=content3[0]
                    userno=content3[2]
                    if content3_user!=username:
                        print(f"{userno} {content3_user}")
                        if flag18:
                                exceptno=12345
                    else:
                        exceptno=int(content3[2])
                        flag18=0
                    cont5+=1
                while flag10:
                    try: 
                        user_input3=input()
                        format_input3=user_input3.strip()
                        format_input3=format_input3.split()
                        len1=len(format_input3)
                        b = [int(string) for string in format_input3]
                        a=sorted(b)
                        lenb=len(b)
                        lena=len(set(a))
                        seq=0
                        while seq<=len1-1:
                            c=int(a[seq])
                            if len(format_input3[seq])==4 and lena==lenb and a[-1]<=cont5 and exceptno!=c:
                                flag10=0
                                seq+=1
                            else:
                                flag10=1
                                break
                        if flag10==1:
                            print("invalid,try again")
                    except:
                        print("invalid,try again")
                
                with open (passwd_dict,"r") as file:
                    for line in file:
                        try:
                            num=int(a[cont4])
                            cont4+=1
                        except:
                            pass
                        content3=line.split(":")
                        
                        content3_path=content3[5]
                        userno=int(content3[2])
                        
                        if userno==num:
                            
                            other_path = f"{content3_path}/.jafr/user-settings.json"
                            with open(other_path, "r") as file:
                                data = file.read()
                                obj = json.loads(data)
                                # print(obj["master"])
                                other_directory = obj["master"]
                                other_meetings=other_directory+"/meetings.md"
                                with open(other_meetings,"a") as file:
                                    file.write(f"\n##### shared by {username}\n")
                                    file.write(f"{content_add}")
                                    if flag17:
                                        print("Meeting shared.")
                                        flag17=0
            
            
            # user_input3=input()
    elif user_input == 3:
        sharinglist=""
        lista=[]

        with open(tasks_dict, "r") as file:
                    for line in file:
                        flag7 = 0
                        if "-" in line:
                            
                                flag6 = 0
                                if flag5 == 1:
                                    print("Which task would you like to share?")
                                    flag5 = 0
                                try:
                                    content1 = line.strip()
                                    
                                    atime1 = time1 = content1.find("Due: ")
                                    time1 = content1[time1+5:].strip()
                                    time1 = time1[:9].strip()
                                    time1 = datetime.datetime.strptime(time1, "%d/%m/%y").date()
                                    # convert time to string to display
                                    btime1 = time1.strftime("%d/%m/%y")
                                    print(f"{cont2}. {content1[2:atime1].strip()} by {btime1}")
                                    sharinglist=content1
                                    lista.append(sharinglist)
                                    
                                    cont2 += 1
                                except:
                                    pass


        

        while flag15:
            
            try:
                user_input4=int(input())
                content_add=lista[user_input4-1]
                flag15=0
            except:
                print("invalid")
        print("Who would you like to share with?")
                
        with open (passwd_dict,"r") as file:
            for line in file:
                content3=line.split(":")
                content3_user=content3[0]
                userno=content3[2]
                if content3_user!=username:
                    print(f"{userno} {content3_user}")
                    if flag18:
                            exceptno=12345
                else:
                    exceptno=int(content3[2])
                    flag18=0

                cont5+=1
                
            
            while flag10:
                    try:
                        user_input3=input()
                        format_input3=user_input3.strip()
                        format_input3=format_input3.split()
                        len1=len(format_input3)
                        b = [int(string) for string in format_input3]
                        a=sorted(b)
                        lenb=len(b)
                        lena=len(set(a))
                        seq=0
                        while seq<=len1-1:
                            c=int(a[seq])
                            if len(format_input3[seq])==4 and lena==lenb and a[-1]<=cont5 and exceptno!=c:
                                    flag10=0
                                    seq+=1
                            else:
                                flag10=1
                                break
                        if flag10==1:
                                print("invalid,try again")
                    except:
                        print("invalid,try again")
                
            with open (passwd_dict,"r") as file:
                for line in file:
                    try:
                        num=int(a[cont4])
                        cont4+=1
                    except:
                        pass
                    content3=line.split(":")
                    
                    content3_path=content3[5]
                    userno=int(content3[2])
                    
                    if userno==num:
                        
                        other_path = f"{content3_path}/.jafr/user-settings.json"
                        with open(other_path, "r") as file:
                            data = file.read()
                            obj = json.loads(data)
                            # print(obj["master"])
                            other_directory = obj["master"]
                            other_meetings=other_directory+"/tasks.md"
                            with open(other_meetings,"a") as file:
                                file.write(f"\n##### shared by {username}\n")
                                file.write(f"{content_add}\n")
                                if flag17:
                                        print("Task shared.")
                                        flag17=0


    elif user_input == 4:
        sharinglist=""
        lista=[]

        with open(meetings_dict, "r") as file:
                    for line in file:
                        flag7 = 0
                        if "-" in line:
                            
                                flag6 = 0
                                if flag5 == 1:
                                    print("Which meeting would you like to share?")
                                    flag5 = 0
                                try:
                                    content2 = line.strip()
                                    atime2 = time2 = content2.find("Scheduled: ")
                                    time2 = content2[time2+11:].strip()
                    

                                    time2 = datetime.datetime.strptime(time2, "%H:%M %d/%m/%y")
                                    time2_days=time2.date()
                                    
                                    btime2 = time2.strftime("%H:%M")
                                    ctime2 = time2.strftime("%d/%m/%y")
                                    print(f"{cont2}. {content2[2:atime2].strip()} on {ctime2} at {btime2}")
                                    sharinglist=content2
                                    lista.append(sharinglist)
                                    
                                    cont2 += 1
                                except:
                                    pass




        while flag15:
            
            try:
                user_input4=int(input())
                content_add=lista[user_input4-1]
                flag15=0
            except:
                print("invalid")
        print("Who would you like to share with?")
                
        with open (passwd_dict,"r") as file:
            for line in file:
                content3=line.split(":")
                content3_user=content3[0]
                userno=content3[2]
                if content3_user!=username:
                    print(f"{userno} {content3_user}")
                    if flag18:
                            exceptno=12345
                else:
                    exceptno=int(content3[2])
                    flag18=0
                cont5+=1
            while flag10:
                    try: 
                        user_input3=input()
                        format_input3=user_input3.strip()
                        format_input3=format_input3.split()
                        len1=len(format_input3)
                        b = [int(string) for string in format_input3]
                        a=sorted(b)
                        lenb=len(b)
                        lena=len(set(a))
                        seq=0
                        while seq<=len1-1:
                            c=int(a[seq])
                            if len(format_input3[seq])==4 and lena==lenb and a[-1]<=cont5 and exceptno!=c:
                                    flag10=0
                                    seq+=1
                            else:
                                flag10=1
                                break
                        if flag10==1:
                                print("invalid,try again")
                    except:
                        print("invalid,try again")
                
            with open (passwd_dict,"r") as file:
                for line in file:
                    try:
                        num=int(a[cont4])
                        cont4+=1
                    except:
                        pass
                    content3=line.split(":")
                    
                    content3_path=content3[5]
                    userno=int(content3[2])
                    
                    if userno==num:
                        
                        other_path = f"{content3_path}/.jafr/user-settings.json"
                        with open(other_path, "r") as file:
                            data = file.read()
                            obj = json.loads(data)
                            # print(obj["master"])
                            other_directory = obj["master"]
                            other_meetings=other_directory+"/meetings.md"
                            with open(other_meetings,"a") as file:
                                file.write(f"\n##### shared by {username}\n")
                                file.write(f"{content_add}\n")
                                if flag17:
                                        print("Meeting shared.")
                                        flag17=0

    elif user_input ==5:
        print("Which directory would you like Jafr to use?")
        try:
            new_path=input("")
            mdir={

                "master": f"{new_path}"

            }
            file_path = os.path.expanduser("~/.jafr/user-settings.json")
            with open(file_path, "w") as file:
                json.dump(mdir, file)
            with open(file_path, "r") as file:
                data = file.read()
                obj = json.loads(data)
                # print(obj["master"])
                directory = obj["master"]


                os.chdir(directory)
                username=os.environ['USER']
                # print(os.environ['USER'])
                #print(directory)
                tasks_dict = directory+"/tasks.md"
                meetings_dict = directory+"/meetings.md"
                print(f"Master directory changed to {new_path}.")
        except:
            sys.stderr.write("Jafr's chosen master directory does not exist.\n")
            sys.exit(1)



    elif user_input == 6:
        break
   

