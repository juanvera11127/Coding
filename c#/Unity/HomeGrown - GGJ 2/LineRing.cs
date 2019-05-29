using System.Collections;
using System.Collections.Generic;
using UnityEngine;


[ExecuteInEditMode]
public class LineRing : MonoBehaviour
{
	public LineRing Next;
	private LineRenderer myLine;
    // Start is called before the first frame update
    void Start()
    {
		myLine = GetComponent<LineRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
		myLine.SetPosition(0, transform.position);
		myLine.SetPosition(1, Next.transform.position);
	}
}
