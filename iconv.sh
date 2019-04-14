to="utf-8"
x=$(file -i $1)
from=${x#*charset=}
if [[ $from != $to ]]; then
    echo "iconv -f $from -t $to $f -o $f"
	iconv -f $from -t $to $f -o $f
fi
