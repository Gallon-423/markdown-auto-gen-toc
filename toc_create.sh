if [ ! -d md_backup ]
then
mkdir md_backup
fi
for i in $(ls | grep .md)
do
cp $i ./md_backup/$i
java -jar single-file-CN.jar $i $i
done