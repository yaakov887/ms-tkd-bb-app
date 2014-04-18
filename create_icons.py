#!/usr/bin/env python

import sys
import os

orig_file = sys.argv[1]
rnme_file = sys.argv[2]

TANGO = '/opt/local/share/icons/Tango/'

XHDPI = 'rsvg-convert -h 96 -w 96 -o /Users/jacoberg2/git/ms-tkd-bb-app/src/res/drawable-xhdpi/'
HDPI = 'rsvg-convert -h 72 -w 72 -o /Users/jacoberg2/git/ms-tkd-bb-app/src/res/drawable-hdpi/'
MDPI = 'rsvg-convert -h 48 -w 48 -o /Users/jacoberg2/git/ms-tkd-bb-app/src/res/drawable-mdpi/'

cmds = [XHDPI, HDPI, MDPI]

for subcmd in cmds:
  cmd = subcmd + rnme_file + ' ' + orig_file
  os.system (cmd)