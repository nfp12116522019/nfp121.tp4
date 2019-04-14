#! /bin/bash
to="utf-8"
x=$(file -i $1)
from=${x#*charset=}
if [[ $from != $to ]]; then
	echo "iconf -f $from -t $to $1 -o $1"
	iconv -f $from -t $to $1 -o $1
fi
