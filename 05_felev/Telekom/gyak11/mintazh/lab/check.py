from pathlib import Path
import subprocess
import sys

version = f"{sys.version_info.major}.{sys.version_info.minor}"
scripts_dir = Path(__file__).parent / ".obfuscated-scripts"

to_run = scripts_dir / version.replace('.', '_') / 'for_students.py'
if to_run.exists():
    proc = subprocess.run([sys.executable, str(to_run), *sys.argv[1:]])
    sys.exit(proc.returncode)
else:
    print(f"The current Python version ({version}) is not supported.")
    print(f"Supported versions are: {[f.name.replace('_', '.') for f in scripts_dir.iterdir()]}")
    sys.exit(1)
